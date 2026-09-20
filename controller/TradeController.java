package edu.ncsu.csc440.poketrader.controller;

import edu.ncsu.csc440.poketrader.dto.TradeDto;
import edu.ncsu.csc440.poketrader.entity.Trade;
import edu.ncsu.csc440.poketrader.service.TradeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/trades")
public class TradeController {

    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    /**
     * GET /api/trades — all trades (sent + received) for the logged-in user
     * 
     * @author Alex Judd
     */
    @GetMapping
    public ResponseEntity<List<TradeDto>> getUserTrades(HttpSession session) {
        final Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        try {
            final List<TradeDto> response = tradeService.getUserTrades(userId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * POST /api/trades
     * Body: { "receiverUserId": 2, "initiatorCardId": 4, "receiverCardId": 2 }
     * 
     * @author Alex Judd
     */
    @PostMapping
    public ResponseEntity<TradeDto> createTrade(@RequestBody Map<String, Long> body,
            HttpSession session) {
        final Long initiatorUserId = (Long) session.getAttribute("userId");
        if (initiatorUserId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        try {
            final Long receiverUserId = (Long) body.get("receiverUserId");
            final Long initiatorCardId = (Long) body.get("initiatorCardId");
            final Long receiverCardId = (Long) body.get("receiverCardId");
            final TradeDto response = tradeService.createTrade(initiatorUserId,
                    receiverUserId, initiatorCardId, receiverCardId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            if (e.getMessage().equals("Missing required trade fields")
                    || e.getMessage().equals("Initiator and receiver must be different users")
                    || e.getMessage().equals("Initiator and receiver cards must be different")) {
                return ResponseEntity.badRequest().build();
            } else if (e.getMessage().equals("Initiator or receiver does not exist")
                    || e.getMessage().equals("Initiator or receiver card does not exist")) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * PUT /api/trades/{id}/accept
     * 
     * @author Alex Judd
     */
    @PutMapping("/{id}/accept")
    public ResponseEntity<TradeDto> acceptTrade(@PathVariable Long id, HttpSession session) {
        final Long receiverUserId = (Long) session.getAttribute("userId");
        if (receiverUserId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        try {
            TradeDto response = tradeService.respondTrade(id, receiverUserId,
                    Trade.TradeStatus.Accepted);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            if (e.getMessage().equals("Missing required response fields")
                    || e.getMessage().equals("Only receiver can respond to this trade")
                    || e.getMessage().equals("Trade has been responded to already")) {
                return ResponseEntity.badRequest().build();
            } else if (e.getMessage().equals("Trade does not exist")) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * PUT /api/trades/{id}/deny
     * 
     * @author Alex Judd
     */
    @PutMapping("/{id}/deny")
    public ResponseEntity<TradeDto> denyTrade(@PathVariable Long id, HttpSession session) {
        final Long receiverUserId = (Long) session.getAttribute("userId");
        if (receiverUserId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        try {
            TradeDto response = tradeService.respondTrade(id, receiverUserId,
                    Trade.TradeStatus.Denied);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            if (e.getMessage().equals("Missing required response fields")
                    || e.getMessage().equals("Only receiver can respond to this trade")
                    || e.getMessage().equals("Trade has been responded to already")) {
                return ResponseEntity.badRequest().build();
            } else if (e.getMessage().equals("Trade does not exist")) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.internalServerError().build();
        }
    }
}
