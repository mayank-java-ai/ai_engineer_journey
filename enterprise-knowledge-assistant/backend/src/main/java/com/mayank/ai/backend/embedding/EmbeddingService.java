package com.mayank.ai.backend.embedding;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmbeddingService {

    public float[] embed(
            String text
    ) {

        Random random =
                new Random(
                        text.hashCode()
                );

        float[] vector =
                new float[384];

        for(int i=0;i<384;i++) {

            vector[i] =
                    random.nextFloat();
        }

        return vector;
    }
}