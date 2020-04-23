package com.marvic.popstats.service;

import com.marvic.popstats.data.CoreBasedStatisticalAreaRepository;
import com.marvic.popstats.domain.CoreBasedStatisticalArea;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.reset;

@ExtendWith(MockitoExtension.class)
class CoreBasedStatisticalAreaServiceTest {
    @Mock
    private CoreBasedStatisticalAreaRepository repository;
    private CoreBasedStatisticalAreaService classUnderTest;

    @BeforeEach
    void setup() {
        reset(repository);
        classUnderTest = new CoreBasedStatisticalAreaService(repository);
    }

    @Test
    void FindByCode_should_call_the_repository_and_return_a_response() {
        String input = "12000";
        Optional<CoreBasedStatisticalArea> expectedResult = Optional.empty();
        given(repository.findById(input)).willReturn(expectedResult);

        Optional<CoreBasedStatisticalArea> result = classUnderTest.findByCode(input);

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void FindAll_should_call_the_repository_and_return_a_response() {
        List<CoreBasedStatisticalArea> expectedResult = List.of();
        given(repository.findAll()).willReturn(expectedResult);

        List<CoreBasedStatisticalArea> result = classUnderTest.findAll();

        assertThat(result).isEqualTo(expectedResult);
    }

    @Nested
    class FindAllBy {
        @Test
        void should_call_the_repository_and_return_a_list() {
            List<String> codeList = List.of("12000");
            List<String> titleList = List.of();
            List<CoreBasedStatisticalArea> expectedResult = List.of();
            given(repository.findAllByTitleInOrCodeIn(titleList, codeList)).willReturn(expectedResult);

            List<CoreBasedStatisticalArea> result = classUnderTest.findAllBy(codeList, titleList);

            assertThat(result).isEqualTo(expectedResult);
        }

        @ParameterizedTest(name = "if codeList is {0} and titleList is {1}")
        @MethodSource("com.marvic.popstats.service.CoreBasedStatisticalAreaServiceTest#dataProvider")
        void should_throw_a_null_pointer_exception(List<String> codeList, List<String> titleList) {
            List<CoreBasedStatisticalArea> expectedResult = List.of();
            given(repository.findAllByTitleInOrCodeIn(titleList, codeList)).willReturn(expectedResult);

            try {
                List<CoreBasedStatisticalArea> result = classUnderTest.findAllBy(codeList, titleList);
                failBecauseExceptionWasNotThrown(NullPointerException.class);
            } catch (NullPointerException e) {
                then(repository).shouldHaveNoInteractions();
            }
        }
    }

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.arguments(null, List.of("12000")),
                Arguments.arguments(List.of("St. Louis, MO-IL"), null));
    }

}