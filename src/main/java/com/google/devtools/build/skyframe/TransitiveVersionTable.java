// Copyright 2018 The Bazel Authors. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.google.devtools.build.skyframe;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.function.Supplier;

/** Readable view of transitive version information. */
public interface TransitiveVersionTable {

  /**
   * @param key instance of {@link
   *     com.google.devtools.build.lib.actions.ActionLookupValue.ActionLookupKey}
   */
  VersionAggregator lookup(SkyKey key);

  /** Encapsulates transitive version information. */
  interface VersionAggregator {
    /**
     * Internally memoized computation of max transitive version.
     *
     * @param key used to lookup this entry
     * @return future with the maximum transitive source version or -1 if no sources were found
     */
    ListenableFuture<Long> getMaxTransitiveVersion(
        SkyKey key,
        InterruptibleSupplier<SkyValue> valueSupplier,
        Supplier<ListenableFuture<Long>> deferredMaxDepVersion);
  }
}
