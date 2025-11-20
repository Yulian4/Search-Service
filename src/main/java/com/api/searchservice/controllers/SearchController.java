import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.searchservice.model.dto.SearchRequestDTO;
import com.api.searchservice.model.dto.SearchResponseDTO;
import com.api.searchservice.service.AccessControlService;
import com.api.searchservice.service.FilterService;
import com.api.searchservice.service.IntegrationService;
import com.api.searchservice.service.RankingService;
import com.api.searchservice.service.SearchService;

import dev.langchain4j.model.chat.ChatModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/search")
public class SearchController {

    private final SearchService searchService;
    private final FilterService filterService;
    private final RankingService rankingService;
    private final AccessControlService accessControlService;
    private final IntegrationService integrationService;
    private final ChatModel openAiModel;

    public SearchController(
            SearchService searchService,
            FilterService filterService,
            RankingService rankingService,
            AccessControlService accessControlService,
            IntegrationService integrationService,
            ChatModel openAiModel
    ) {
        this.searchService = searchService;
        this.filterService = filterService;
        this.rankingService = rankingService;
        this.accessControlService = accessControlService;
        this.integrationService = integrationService;
        this.openAiModel = openAiModel;
    }

    @PostMapping
    public ResponseEntity<SearchResponseDTO> searchRecipes(
            @RequestHeader("Authorization") String token,
            @RequestBody SearchRequestDTO request
    ) {
        log.info("🔍 Iniciando búsqueda de recetas: {}", request.getQuery());

        try {
            // 1️⃣ VALIDAR TOKEN VIA AUTH SERVICE
            String userId = accessControlService.validateToken(token);

            // 2️⃣ INTERPRETAR INTENCIÓN CON IA
            var interpreted = searchService.interpretQuery(request, openAiModel);

            // 3️⃣ CONSULTAR RECETAS CANDIDATAS EN RECIPE SERVICE
            var candidates = searchService.searchCandidates(interpreted);

            // 4️⃣ APLICAR FILTROS POR RESTRICCIONES ALIMENTICIAS
            var filtered = filterService.applyUserRestrictions(userId, candidates);

            // 5️⃣ APLICAR RANKING
            var ranked = rankingService.rankRecipes(filtered, interpreted);

            // 6️⃣ LIMITAR RESULTADOS SEGÚN EL TIPO DE USUARIO
            var limited = accessControlService.applyUserVisibilityLimits(userId, ranked);

            // 7️⃣ INTEGRAR CON RECIPE SERVICE PARA TRAER DETALLES COMPLETOS
            var fullDetails = integrationService.getRecipeDetails(limited);

            // 8️⃣ RESPONDER AL CLIENTE
            SearchResponseDTO response = new SearchResponseDTO(fullDetails);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("❌ Error en búsqueda inteligente", e);
            return ResponseEntity.badRequest().body(null);
        }
    }
}