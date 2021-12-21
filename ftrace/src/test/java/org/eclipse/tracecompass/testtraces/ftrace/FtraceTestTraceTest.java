/*******************************************************************************
 * Copyright (c) 2021 Ericsson
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/


package org.eclipse.tracecompass.testtraces.ftrace;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * Basic tests for the FtraceTestTraces
 */
public class FtraceTestTraceTest {

    /**
     * Test that all configured traces are present.
     */
    @Test
    public void testTracesExist() {
        for (FtraceTestTrace trace : FtraceTestTrace.values()) {
            assertNotNull(trace.getTraceURL());
        }
    }
}
