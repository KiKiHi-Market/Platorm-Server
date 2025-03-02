//package org.jiyoung.kikihi.platform.adapter.in.web;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.jiyoung.kikihi.common.response.ApiResponse;
//import org.jiyoung.kikihi.common.response.CustomException;
//import org.jiyoung.kikihi.platform.adapter.out.aws.elasticSearch.ElasticSearchService;
//import org.jiyoung.kikihi.platform.adapter.out.jpa.user.User;
//import org.jiyoung.kikihi.platform.adapter.out.jpa.product.Product;
//import org.springframework.data.domain.Limit;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//import static org.jiyoung.kikihi.common.response.ErrorCode.INTERNAL_SERVER_ERROR;
//
//@Slf4j
//@RestController
//@RequestMapping("/api/v1/productSearch")
//@RequiredArgsConstructor
//public class ProductSearchController {
//
//    private final ElasticSearchService elasticSearchService;
//
//    @GetMapping
//    public ApiResponse<List<Product>> search(@RequestParam(name = "keyword") String keyword) {
//        try{
//        User user=User.from("hjy020714@gmail.com","123456");
//        List<Product> products = elasticSearchService.searchByKeyword(keyword, Limit.of(10));
//            System.out.println("products:"+products);
//        return ApiResponse.ok(products);}
//        catch (Exception e){
//            return ApiResponse.fail(new CustomException(INTERNAL_SERVER_ERROR));
//        }
//    }
//
//    // 와일드카드 쿼리로 제목을 포함하는 상품 검색 (페이징 처리)
//    @GetMapping("/searchWithWildcard")
//    public Page<Product> searchProductsWithWildcard(@RequestParam("keyword") String keyword, Pageable pageable) {
//        return elasticSearchService.searchByKeywordWithWildcard(keyword, pageable);
//    }
//
//
//    // 새로운 상품 저장 (POST 요청)
//    @PostMapping
//    public ApiResponse<String> saveProduct(@RequestBody Product product) {
//        try {
//            elasticSearchService.saveProduct(product);  // ProductService의 saveProduct 호출
//            return ApiResponse.ok("index 저장됨");
//        } catch (Exception e) {
//            return ApiResponse.fail(new CustomException(INTERNAL_SERVER_ERROR));
//        }
//    }
//
//
//}
