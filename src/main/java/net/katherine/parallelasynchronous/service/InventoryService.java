package net.katherine.parallelasynchronous.service;



import net.katherine.parallelasynchronous.domain.Inventory;
import net.katherine.parallelasynchronous.domain.ProductOption;

import java.util.concurrent.CompletableFuture;

import static net.katherine.parallelasynchronous.util.CommonUtil.delay;


public class InventoryService {
    public Inventory addInventory(ProductOption productOption) {
        delay(500);
        return Inventory.builder()
                .count(2).build();

    }

    public CompletableFuture<Inventory> addInventory_CF(ProductOption productOption) {

        return CompletableFuture.supplyAsync(() -> {
            delay(500);
            return Inventory.builder()
                    .count(2).build();
        });

    }
}
