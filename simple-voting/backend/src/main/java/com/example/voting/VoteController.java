package com.example.voting;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class VoteController {

    private final VoteMapper mapper;

    // 取得所有投票項目
    @GetMapping("/items")
    public List<VoteItem> getItems() {
        return mapper.getItems();
    }

    // 新增項目（後台）
    @PostMapping("/items")
    public void addItem(@RequestBody ItemReq req) {
        mapper.addItem(req.getItemName());
    }

    // 更新項目（後台）
    @PutMapping("/items/{id}")
    public void updateItem(@PathVariable int id, @RequestBody ItemReq req) {
        mapper.updateItem(id, req.getItemName());
    }

    // 刪除項目（後台）
    @DeleteMapping("/items/{id}")
    public void deleteItem(@PathVariable int id) {
        mapper.deleteItem(id);
    }

    // 投票（可多選，每個 itemId 呼叫一次）
    @PostMapping("/vote")
    public void vote(@RequestBody VoteReq req) {
        for (int itemId : req.getItemIds()) {
            mapper.vote(req.getVoterName(), itemId);
        }
    }

    // ---- Request 物件 ----
    @Data static class ItemReq { private String itemName; }
    @Data static class VoteReq { private String voterName; private List<Integer> itemIds; }
}
