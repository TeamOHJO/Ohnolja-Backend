package com.example.yanolja.domain.review.service;

import com.example.yanolja.domain.accommodation.repository.AccommodationRoomRepository;
import com.example.yanolja.domain.review.dto.AccommodationReviewResponse;
import com.example.yanolja.domain.review.dto.RoomReviewResponse;
import com.example.yanolja.domain.review.entity.Review;
import com.example.yanolja.domain.review.repository.ReviewRepository;
import com.example.yanolja.domain.user.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final AccommodationRoomRepository roomRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository,
        UserRepository userRepository,
        AccommodationRoomRepository roomRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }

//    @Override
//    @Transactional
//    public RoomReviewResponse createReview(Long roomId, ReviewCreateRequest request) {
//        AccommodationRooms room = roomRepository.findById(roomId)
//            .orElseThrow(() -> new ReviewNotFoundException(ErrorCode.INVALID_ACCOMMODATION_ID));
//
//        User user = userRepository.findById(request.getUserId())
//            .orElseThrow(() -> new ReviewNotFoundException(ErrorCode.USER_NOT_FOUND));
//
//        Review review = Review.builder()
//            .room(room)
//            .user(user)
//            .reviewContent(request.getReviewContent())
//            .image(request.getImage())
//            .star(request.getStar())
//            .build();
//        review.setRoom(room);
//        review.setUser(user);
//        review.setReviewContent(request.getReviewContent());
//        review.setImage(request.getImage());
//        review.setStar(request.getStar());
//
//        Review savedReview = reviewRepository.save(review);
//
//        return new RoomReviewResponse(
//            savedReview.getReviewId(),
//            roomId, user.getUserId(),
//            savedReview.getReviewContent(),
//            savedReview.getImage(),
//            savedReview.getStar(),
//            savedReview.getUpdatedAt());
//    }


    @Override
    public List<AccommodationReviewResponse> getReviewsByAccommodationId(Long accommodationId) {
        return reviewRepository.findByAccommodationId(accommodationId).stream()
            .map(review -> new AccommodationReviewResponse(
                review.getReviewId(),
                review.getRoom().getRoomId(),
                review.getUser().getUsername(),
                review.getRoom().getAccommodation().getAccommodationName(),
                review.getRoom().getAccommodation().getCategory(),
                review.getReviewContent(),
                review.getStar(),
                review.getUpdatedAt()))
            .collect(Collectors.toList());
    }

//    @Override
//    @Transactional
//    public RoomReviewResponse updateReview(Long reviewId, ReviewUpdateRequest request) {
//        Review review = reviewRepository.findById(reviewId)
//            .orElseThrow(() -> new ReviewNotFoundException(ErrorCode.REVIEW_NOT_FOUND));
//
//        review.setReviewContent(request.getReviewContent());
//        review.setImage(request.getImage());
//        review.setStar(request.getStar());
//
//        return new RoomReviewResponse(
//            review.getReviewId(),
//            review.getRoom().getRoomId(),
//            review.getUser().getUserId(),
//            review.getReviewContent(),
//            review.getImage(),
//            review.getStar(),
//            review.getUpdatedAt());
//    }
//
//    @Override
//    @Transactional
//    public void deleteReview(Long reviewId) {
//        Review review = reviewRepository.findById(reviewId)
//            .orElseThrow(() -> new ReviewNotFoundException(ErrorCode.REVIEW_NOT_FOUND));
//
//        // 리뷰가 활성된 상태가 아닌 경우 예외 발생 (수정 중, 관리자만 볼 경우 등등)
//        if (!review.isActive()) {
//            throw new ReviewNotFoundException(ErrorCode.REVIEW_NOT_FOUND);
//        }
//
//        reviewRepository.deleteById(reviewId);
//    }

}
