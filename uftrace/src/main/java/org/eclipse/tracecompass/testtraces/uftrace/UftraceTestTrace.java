/*******************************************************************************
 * Copyright (c) 2025 Ericsson
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package org.eclipse.tracecompass.testtraces.ftrace;

import java.net.URL;

/**
 * Here is the list of the available test traces for the uftrace parser.
 *
 * @author Matthew Khouzam
 */
public enum UftraceTestTrace {
    /**
     * Example uftrace taken from foobar
     *
     * <pre>
     * Trace Size: 240 K
     * Tracer: uftrace v0.9.3 ( dwarf python tui perf sched dynamic )
     * Event count: 16
     * Kernel version: 6.5
     * Trace length: 300 us
     * </pre>
     *
     * The file is obtained by running uftrace on foobar
     */
    TEST_FOOBAR("uftrace-foobar-demo/uftrace.data", 16, 1),


    private final String fTraceName;
    private final int fNbEvent;
    private int fDuration;

    private FtraceTestTrace(String traceName, int nbEvent, int time) {
        fTraceName = traceName;
        fNbEvent = nbEvent;
        fDuration = time;
    }

    public URL getTraceURL() {
        URL url = this.getClass().getResource(fTraceName);
        if (url == null) {
            /* Project configuration problem? */
            throw new IllegalStateException("Test trace not found");
        }
        return url;
    }

    /**
     * Get the number of events for a trace
     *
     * @return the number of events, -1 if irrelevant
     */
    public int getNbEvents() {
        return fNbEvent;
    }

    /**
     * Get the duration in seconds of a trace
     *
     * @return the duration in seconds of a trace, -1 if irrelevant
     */
    public int getDuration() {
        return fDuration;
    }
}
