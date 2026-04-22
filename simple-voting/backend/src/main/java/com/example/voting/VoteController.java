package com.example.voting;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class VoteController {

    private final VoteMapper mapper;

    // 手動寫建構子，不用 @RequiredArgsConstructor
    public VoteController(VoteMapper mapper) {
        this.mapper = mapper;
    }

    @GetMapping("/items")
    public List<VoteItem> getItems() {
        return mapper.getItems();
    }

    @PostMapping("/items")
    public void addItem(@RequestBody ItemReq req) {
        mapper.addItem(req.getItemName());
    }

    @PutMapping("/items/{id}")
    public void updateItem(@PathVariable int id, @RequestBody ItemReq req) {
        mapper.updateItem(id, req.getItemName());
    }

    @DeleteMapping("/items/{id}")
    public void deleteItem(@PathVariable int id) {
        mapper.deleteItem(id);
    }

    @PostMapping("/vote")
    public void vote(@RequestBody VoteReq req) {
        for (int itemId : req.getItemIds()) {
            mapper.vote(req.getVoterName(), itemId);
        }
    }

    static class ItemReq {
        private String itemName;
        public String getItemName() { return itemName; }
        public void setItemName(String itemName) { this.itemName = itemName; }
    }

    static class VoteReq {
        private String voterName;
        private List<Integer> itemIds;
        public String getVoterName() { return voterName; }
        public void setVoterName(String voterName) { this.voterName = voterName; }
        public List<Integer> getItemIds() { return itemIds; }
        public void setItemIds(List<Integer> itemIds) { this.itemIds = itemIds; }
    }
}