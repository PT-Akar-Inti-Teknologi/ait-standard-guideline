package org.ait.project.guideline.example.modules.post.service.delegate;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.ait.project.guideline.example.modules.post.model.entity.JsonPlaceHolderPost;
import org.ait.project.guideline.example.modules.post.model.repository.JsonPlaceHolderPostRepository;
import org.ait.project.guideline.example.modules.post.service.delegate.impl.JsonPlaceHolderPostDelegateImpl;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
public class JsonPlaceHolderPostDelegateTest {

	@Mock
	private JsonPlaceHolderPostRepository jsonPlaceHolderPostRepository;
	
	@InjectMocks
	private JsonPlaceHolderPostDelegateImpl jsonPlaceHolderPostDelegate;
	
    private EasyRandom easyRandom = new EasyRandom();
	
	@Test
	public void testGetAllPost() {
		Long numberOfObjects = Math.abs(easyRandom.nextLong(1, Integer.MAX_VALUE/1024));
		List<JsonPlaceHolderPost> expectedList = easyRandom.objects(JsonPlaceHolderPost.class, numberOfObjects.intValue()).collect(Collectors.toList()); 
		when(jsonPlaceHolderPostRepository.findAll()).thenReturn(expectedList);
		
		List<JsonPlaceHolderPost> actualList = jsonPlaceHolderPostDelegate.getAllPost();
		assertEquals(expectedList.size(), actualList.size());
		for (int i = 0; i < expectedList.size(); i++) {
			JsonPlaceHolderPost expected = expectedList.get(i);
			JsonPlaceHolderPost actual = actualList.get(i);
			
			assertEquals(expected.getBody(), actual.getBody());
			assertEquals(expected.getCreateAt(), actual.getCreateAt());
			assertEquals(expected.getId(), actual.getId());
			assertEquals(expected.getTitle(), actual.getTitle());
			assertEquals(expected.getUserId(), actual.getUserId());
		}
	}

	@Test
	public void testGetAllPostPage() {
		
		Long numberOfObjects = Math.abs(easyRandom.nextLong(1, Integer.MAX_VALUE/1024));
		List<JsonPlaceHolderPost> expectedList = easyRandom.objects(JsonPlaceHolderPost.class, numberOfObjects.intValue()).collect(Collectors.toList());
		PageImpl<JsonPlaceHolderPost> expectedPage = new PageImpl<>(expectedList, Pageable.ofSize(10), numberOfObjects);
		Pageable pageableToUse = expectedPage.getPageable();
		when(jsonPlaceHolderPostRepository.findAll(pageableToUse)).thenReturn(expectedPage);
		
		Page<JsonPlaceHolderPost> actualPage = jsonPlaceHolderPostDelegate.getAllPostPage(pageableToUse);
		List<JsonPlaceHolderPost> actualList = actualPage.getContent();
		
		assertEquals(expectedPage.getSize(), actualPage.getSize());
		for (int i = 0; i < expectedList.size(); i++) {
			JsonPlaceHolderPost expected = expectedList.get(i);
			JsonPlaceHolderPost actual = actualList.get(i);
			
			assertEquals(expected.getBody(), actual.getBody());
			assertEquals(expected.getCreateAt(), actual.getCreateAt());
			assertEquals(expected.getId(), actual.getId());
			assertEquals(expected.getTitle(), actual.getTitle());
			assertEquals(expected.getUserId(), actual.getUserId());
		}
	}

	@Test
	public void testGetPostById() {
		JsonPlaceHolderPost expectedObject = easyRandom.nextObject(JsonPlaceHolderPost.class);
		
		when(jsonPlaceHolderPostRepository.findById(expectedObject.getId())).thenReturn(Optional.of(expectedObject));
		
		JsonPlaceHolderPost actualObject = jsonPlaceHolderPostDelegate.getPostById(expectedObject.getId());
		
		assertEquals(expectedObject.getBody(), actualObject.getBody());
		assertEquals(expectedObject.getCreateAt(), actualObject.getCreateAt());
		assertEquals(expectedObject.getId(), actualObject.getId());
		assertEquals(expectedObject.getTitle(), actualObject.getTitle());
		assertEquals(expectedObject.getUserId(), actualObject.getUserId());
	}
	
	@Test
	public void testSave() {
		
		
		JsonPlaceHolderPost toSave = easyRandom.nextObject(JsonPlaceHolderPost.class);
		toSave.setId(null);
		JsonPlaceHolderPost expectedAfterSave = toSave;
		
		Integer savedId = easyRandom.nextInt(1, Integer.MAX_VALUE/1024);
		expectedAfterSave.setId(savedId);
		
		when(jsonPlaceHolderPostRepository.save(toSave)).thenReturn(expectedAfterSave);
		JsonPlaceHolderPost actualAfterSave = jsonPlaceHolderPostDelegate.save(toSave);
		
		assertEquals(expectedAfterSave.getBody(), actualAfterSave.getBody());
		assertEquals(expectedAfterSave.getCreateAt(), actualAfterSave.getCreateAt());
		assertEquals(expectedAfterSave.getId(), actualAfterSave.getId());
		assertEquals(expectedAfterSave.getTitle(), actualAfterSave.getTitle());
		assertEquals(expectedAfterSave.getUserId(), actualAfterSave.getUserId());
	}

	@Test
	public void testSaveAll() {
		int higherBoundId = easyRandom.nextInt(1, Integer.MAX_VALUE);
		int lowerBoundId = higherBoundId - 5;
		int expectedSize = higherBoundId - lowerBoundId;
		
		Stream<JsonPlaceHolderPost> streamToSave = easyRandom.objects(JsonPlaceHolderPost.class, expectedSize);
		List<JsonPlaceHolderPost> toSave = streamToSave.collect(Collectors.toList());
		toSave.forEach(raw -> raw.setId(null));
		List<JsonPlaceHolderPost> afterSave = toSave;
		Integer pointerIndex = 0;
		for (int i = lowerBoundId; i < higherBoundId; i++) {
			afterSave.get(pointerIndex).setId(i);
			pointerIndex += 1;
		}
		
		when(jsonPlaceHolderPostRepository.saveAll(toSave)).thenReturn(afterSave);
		
		List<JsonPlaceHolderPost> actualSaved = jsonPlaceHolderPostDelegate.saveAll(toSave);
		
		for (int i = 0; i < actualSaved.size(); i++) {
			JsonPlaceHolderPost actual = actualSaved.get(i);
			JsonPlaceHolderPost expected = afterSave.get(i);
			
			assertEquals(expected.getBody(), actual.getBody());
			assertEquals(expected.getCreateAt(), actual.getCreateAt());
			assertEquals(expected.getId(), actual.getId());
			assertEquals(expected.getTitle(), actual.getTitle());
			assertEquals(expected.getUserId(), actual.getUserId());
		}
		
		
	}

	

}
