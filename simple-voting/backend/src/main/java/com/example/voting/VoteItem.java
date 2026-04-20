package com.example.voting;

import lombok.Data;

@Data
public class VoteItem {
    private Integer itemId;
    private String  itemName;
    private Integer voteCount;
}
