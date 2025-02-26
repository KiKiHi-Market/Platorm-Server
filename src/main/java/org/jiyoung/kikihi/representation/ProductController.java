package org.jiyoung.kikihi.representation;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jiyoung.kikihi.domain.common.response.ApiResponse;
//import org.jiyoung.kikihi.infrastructure.storage.S3Service;
import org.jiyoung.kikihi.domain.product.dto.request.ProductTemporarySaveDto;
import org.jiyoung.kikihi.domain.product.dto.response.ProductResponseDto;
import org.jiyoung.kikihi.domain.product.service.ProductService;
import org.jiyoung.kikihi.domain.product.service.ProductTemporarySaveService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

//    private final S3Service s3Service;
    private final ProductService productService;
    private final ProductTemporarySaveService temporarySaveService;

//     1. 상품 등록 -> admin에서 해야함
    //관리자 혹은 멤버가 상품을 추가한다.권한은 나중에 설정할거다
    //multipartfile에는 상품 썸네일을 받아서 저장한다.
    // 등록한 사람도 저장한다.
    @PostMapping("/submit")
    public ApiResponse<String> submitTemporaryProduct(HttpSession session) {
        ProductTemporarySaveDto tempData = (ProductTemporarySaveDto) session.getAttribute("tempProductData");

        // 세션에서 받은 데이터를 최종적으로 제품 생성
        productService.saveFinalProduct(tempData);

        session.removeAttribute("tempProductData"); // 세션에서 임시 데이터 제거

        return ApiResponse.ok("Product saved successfully");
    }

    // 임시 저장 요청
    @PostMapping("/temporary/save")
    public ApiResponse<String> saveTemporaryProduct(@RequestBody ProductTemporarySaveDto Dto) {
        // 텍스트, 이미지, 옵션, 태그 정보를 서비스에 전달하여 임시 저장
        temporarySaveService.saveTemporaryProduct(Dto);
        return ApiResponse.ok("Product data saved temporarily");
    }

    // 미리보기


    // 상품 삭제
    @DeleteMapping("/{productId}")
    public ApiResponse<String> deleteProduct(@PathVariable("productId") Long productId) {
        productService.deleteProduct(productId);
        return ApiResponse.ok("Deleted completely! ");
    }

    // 4. 상품 상세 조회
//    @GetMapping("/{productId}")
//    public ApiResponse<ProductResponseDto> getProductById(@PathVariable Long productId) {
//        ProductResponseDto productResponseDto = productService.getProductById(productId);
//        return ApiResponse.ok(productResponseDto);
//    }
//
//    // 5. 상품 목록 조회
//    @GetMapping
//    public ApiResponse<List<ProductResponseDto>> getAllProducts() {
//        List<ProductResponseDto> productList = productService.getAllProducts();
//        return ApiResponse.ok(productList);
//    }
//
//    // 6. 좋아요한 목록 조회
//    @GetMapping("/liked")
//    public ApiResponse<List<ProductResponseDto>> getLikedProducts(@RequestParam Long userId) {
//        List<ProductResponseDto> likedProducts = likedProductService.getLikedProductsByUser(userId);
//        return ApiResponse.ok(likedProducts);
//    }
//
//    // 7. 상품 상세 조회 (host)
//    @GetMapping("/host/{productId}")
//    public ApiResponse<ProductResponseDto> getProductDetailHost(@PathVariable Long productId) {
//        ProductResponseDto productResponseDto = productService.getProductDetailHost(productId);
//        return ApiResponse.ok(productResponseDto);
//    }
//
//    // 8. 상품 좋아요 등록
//    @PostMapping("/{productId}/like")
//    public ApiResponse<String> likeProduct(@RequestParam Long userId, @PathVariable Long productId) {
//        likedProductService.likeProduct(userId, productId);
//        return ApiResponse.ok("Product liked okfully");
//    }
//
//    // 9. 상품 좋아요 삭제
//    @DeleteMapping("/{productId}/like")
//    public ApiResponse<String> unlikeProduct(@RequestParam Long userId, @PathVariable Long productId) {
//        likedProductService.unlikeProduct(userId, productId);
//        return ApiResponse.ok("Product unliked okfully");
//    }

    //10. 카테고리 전체 목록 depth별로 조회
    //11. 카테고리 상품 목록 조건 조회
    //12. 상품 목록 조건 검색
    //13. 최신 상품 조회
    //14. 전체 카테고리 할인율 순 limit 개 불러오기
    //15. 카테고리별 할인율 높은 순으로 limit개 불러오기
    //16

}
