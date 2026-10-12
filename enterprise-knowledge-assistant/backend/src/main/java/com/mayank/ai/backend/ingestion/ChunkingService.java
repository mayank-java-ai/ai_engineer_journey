package com.mayank.ai.backend.ingestion;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChunkingService {

    public List<String> chunk(
            String content
    ) {

        List<String> chunks =
                new ArrayList<>();

        int size = 200;

        for(int i = 0;
            i < content.length();
            i += size) {

            chunks.add(

                    content.substring(
                            i,
                            Math.min(
                                    i + size,
                                    content.length()
                            )
                    )
            );
        }

        return chunks;
    }
}