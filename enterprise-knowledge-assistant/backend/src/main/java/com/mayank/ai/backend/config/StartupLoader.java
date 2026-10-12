package com.mayank.ai.backend.config;

import com.mayank.ai.backend.service.VectorStoreService;
import com.mayank.ai.backend.vector.VectorDocument;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class StartupLoader {

    private final VectorStoreService vectorStoreService;

    public StartupLoader(
            VectorStoreService vectorStoreService
    ) {
        this.vectorStoreService =
                vectorStoreService;
    }

    @PostConstruct
    public void loadData() {

        vectorStoreService.addDocument(

                new VectorDocument(
                        "leave-policy",
                        """
                        Paternity Leave : 15 Days

                        Maternity Leave : 26 Weeks

                        Annual Leave : 30 Days
                        """
                )
        );

        vectorStoreService.addDocument(

                new VectorDocument(
                        "travel-policy",
                        """
                        Travel Allowance : 5000 INR

                        Flight reimbursement allowed

                        Hotel reimbursement allowed
                        """
                )
        );
    }
}