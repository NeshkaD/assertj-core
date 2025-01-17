/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2022 the original author or authors.
 */
package org.assertj.core.api.throwable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.error.ShouldHaveRootCause.shouldHaveRootCause;
import static org.assertj.core.util.AssertionsUtil.expectAssertionError;
import static org.assertj.core.util.FailureMessages.actualIsNull;

import org.junit.jupiter.api.Test;

class ThrowableAssert_getRootCause_Test {

  private final Throwable rootCause = new NullPointerException();
  private final Throwable cause = new RuntimeException(rootCause);

  @Test
  void should_return_throwable_assertions_for_actual_root_cause() {
    // GIVEN
    Throwable throwable = new Throwable(cause);
    // WHEN/THEN
    assertThat(throwable).getRootCause()
                         .isNotSameAs(cause)
                         .isSameAs(rootCause);
  }

  @Test
  void should_fail_if_actual_has_no_root_cause() {
    // GIVEN
    Throwable actual = new Throwable();
    // WHEN
    AssertionError error = expectAssertionError(() -> assertThat(actual).getRootCause());
    // THEN
    assertThat(error).hasMessage(shouldHaveRootCause(actual).create());
  }

  @Test
  void should_fail_if_actual_is_null() {
    // GIVEN
    Throwable actual = null;
    // WHEN
    AssertionError error = expectAssertionError(() -> assertThat(actual).getRootCause());
    // THEN
    assertThat(error).hasMessage(actualIsNull());
  }

}
