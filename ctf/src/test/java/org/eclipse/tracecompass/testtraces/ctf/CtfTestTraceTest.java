/*******************************************************************************
 * Copyright (c) 2015, 2024 Efficios Inc., Alexaandre Montplaisir and otherrs
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package org.eclipse.tracecompass.testtraces.ctf;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * Basic tests for the CtfTestTraces
 */
public class CtfTestTraceTest {

    /**
     * Test that all configured traces are present.
     */
    @Test
    public void testTracesExist() {
        for (CtfTestTrace trace : CtfTestTrace.values()) {
            assertNotNull(trace.getTraceURL());
        }
    }
}
