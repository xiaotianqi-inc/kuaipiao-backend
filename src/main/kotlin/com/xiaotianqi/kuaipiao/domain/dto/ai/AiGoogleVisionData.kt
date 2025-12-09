package com.xiaotianqi.kuaipiao.domain.dto.ai

import com.xiaotianqi.kuaipiao.enums.FeatureType

data class GoogleVisionRequest(
    val imageData: ByteArray,
    val features: List<FeatureType> = listOf(FeatureType.TEXT_DETECTION)
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        other as GoogleVisionRequest
        if (!imageData.contentEquals(other.imageData)) return false
        if (features != other.features) return false
        return true
    }

    override fun hashCode(): Int {
        var result = imageData.contentHashCode()
        result = 31 * result + features.hashCode()
        return result
    }
}

data class GoogleVisionResponse(
    val textAnnotations: List<TextAnnotation>,
    val fullText: String? = null
)

data class TextAnnotation(
    val text: String,
    val description: String,
    val boundingBox: BoundingBox? = null,
    val confidence: Double? = null
)

data class BoundingBox(
    val vertices: List<Vertex>
)

data class Vertex(
    val x: Int,
    val y: Int
)

data class VisionRequest(
    val requests: List<ImageRequest>
)

data class ImageRequest(
    val image: ImageContent,
    val features: List<Feature>
)

data class ImageContent(
    val bytes: String? = null,
    val content: String? = null
)

data class Feature(
    val type: String,
    val maxResults: Int = 10
)

data class VisionResponse(
    val responses: List<VisionAnnotateImageResponse>
)

data class VisionAnnotateImageResponse(
    val fullTextAnnotation: FullTextAnnotation? = null,
    val textAnnotations: List<EntityAnnotation>? = null,
    val labelAnnotations: List<EntityAnnotation>? = null,
    val logoAnnotations: List<EntityAnnotation>? = null,
    val error: VisionError? = null
)

data class FullTextAnnotation(
    val text: String,
    val pages: List<Page> = emptyList()
)

data class EntityAnnotation(
    val description: String,
    val score: Float? = null,
    val confidence: Float? = null,
    val boundingPoly: BoundingPoly? = null
)

data class BoundingPoly(
    val vertices: List<Vertex> = emptyList()
)

data class VisionError(
    val code: Int,
    val message: String
)

data class Page(
    val width: Int? = null,
    val height: Int? = null,
    val blocks: List<Block>? = null
)

data class Block(
    val blockType: String? = null,
    val confidence: Float? = null,
    val boundingBox: BoundingBox? = null
)

data class DocumentStructureAnalysis(
    val fullText: String,
    val labels: List<String>,
    val logos: List<String>,
    val pages: List<Page>
)

data class GeminiRequest(
    val contents: List<GeminiContent>,
    val generationConfig: GenerationConfig? = null,
    val safetySettings: List<SafetySetting>? = null
)

data class GeminiContent(
    val parts: List<GeminiPart>,
    val role: String = "user"
)

data class GeminiPart(
    val text: String
)

data class GenerationConfig(
    val temperature: Double = 0.1,
    val topK: Int = 40,
    val topP: Double = 0.95,
    val maxOutputTokens: Int = 8192
)

data class SafetySetting(
    val category: String,
    val threshold: String
)

data class GeminiResponse(
    val candidates: List<GeminiCandidate>
)

data class GeminiCandidate(
    val content: GeminiContent,
    val finishReason: String? = null,
    val safetyRatings: List<SafetyRating>? = null
)

data class SafetyRating(
    val category: String,
    val probability: String
)