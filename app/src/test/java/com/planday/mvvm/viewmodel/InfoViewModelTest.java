package com.planday.mvvm.viewmodel;

import android.test.suitebuilder.annotation.MediumTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.util.Collections;

import com.planday.biz.viewmodel.InfoViewModel;
import com.planday.mvvm.R;
import com.planday.biz.service.InfoService;
import rx.Observable;
import rx.observers.TestSubscriber;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@MediumTest
@RunWith(JUnit4.class)
public class InfoViewModelTest {
    private InfoViewModel infoViewModel;
    private InfoService infoService;
    private TestSubscriber<Boolean> testSubscriber;

    @Before
    public void setUpViewModel() {
        // Mock info api
        infoService = Mockito.mock(InfoService.class);
        when(infoService.submitInfo(anyString(), anyString()))
                .thenReturn(Observable.just(true));

        // Create test viewmodel
        infoViewModel = new InfoViewModel(infoService);
        testSubscriber = TestSubscriber.create();
    }

    @Test
    public void inputValidIdCard() throws Exception {
        infoViewModel.setIdCard("412341234");
        assertEquals(infoViewModel.idCardError().get(), R.string.empty);
    }

    @Test
    public void inputInvalidIdCard() throws Exception {
        infoViewModel.setIdCard("abcdabcdabcd");
        assertEquals(infoViewModel.idCardError().get(), R.string.error_id_card);
    }

    @Test
    public void inputValidEmail() throws Exception {
        infoViewModel.setEmail("abc@gmail.com");
        assertEquals(infoViewModel.emailError().get(), R.string.empty);
    }

    @Test
    public void inputInvalidEmail() throws Exception {
        infoViewModel.setEmail("__123$$@gm.co");
        assertEquals(infoViewModel.emailError().get(), R.string.error_email);
    }

    @Test
    public void canSubmit() throws Exception {
        infoViewModel.setIdCard("412341234");
        infoViewModel.setEmail("abc@gmail.com");
        assertTrue(infoViewModel.canSubmit().get());
    }

    @Test
    public void cannotSubmitInvalidEmail() throws Exception {
        infoViewModel.setIdCard("412341234");
        infoViewModel.setEmail("ndc###@gmail.com");
        assertFalse(infoViewModel.canSubmit().get());
    }

    @Test
    public void cannotSubmitInvalidIdCard() throws Exception {
        infoViewModel.setIdCard("412123123341234123");
        infoViewModel.setEmail("abc@gmail.com");
        assertFalse(infoViewModel.canSubmit().get());
    }

    @Test
    public void cannotSubmitEmptyEmail() throws Exception {
        infoViewModel.setIdCard("412341234");
        infoViewModel.setEmail("");
        assertFalse(infoViewModel.canSubmit().get());
    }

    @Test
    public void cannotSubmitEmptyIdCard() throws Exception {
        infoViewModel.setEmail("abc@gmail.com");
        infoViewModel.setIdCard("");
        assertFalse(infoViewModel.canSubmit().get());
    }

    @Test
    public void submit() throws Exception {
        infoViewModel.setIdCard("412341234");
        infoViewModel.setEmail("abc@gmail.com");
        infoViewModel.submit().subscribe(testSubscriber);
        testSubscriber.assertReceivedOnNext(Collections.singletonList(true));
        verify(infoService).submitInfo("412341234", "abc@gmail.com");
    }
}