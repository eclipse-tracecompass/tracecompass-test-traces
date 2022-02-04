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

import java.net.URL;

/**
 * Here is the list of the available test traces for the Ftrace parser.
 *
 * @author Matthew Khouzam
 */
public enum FtraceTestTrace {
    /**
     * Example kernel trace
     *
     * <pre>
     * Trace Size: 8 MB
     * Tracer: FTrace 2.6
     * Event count: 2880
     * Kernel version: 5.2
     * Trace length: 1s
     * </pre>
     *
     * The file is obtained by running trace-cmd for the ls command: trace-cmd
     * record -e all ls on a machine with 4 CPU cores.
     */
    TEST_2_6_MULTIPLE_CPUS("/2.6/trace_4cpu.dat", 2880, 1),

    /**
     * <pre>
     * Trace Size: 6.4 MB
     * Tracer: FTrace 2.6
     * Event count: 1
     * Kernel version: 5.2
     * Trace length: 1s
     * </pre>
     *
     * The file containing a single event to parse. For this file, I followed the
     * example in the man pages for event_fd
     * (https://man7.org/linux/man-pages/man2/eventfd.2.html). To obtain the trace,
     * run trace-cmd while running the example with the flag -e sys_enter_eventfd2.
     * This event should occur only once.
     */
    TEST_2_6_SINGLE_EVENT("/2.6/trace_single_event.dat", 1, 1),

    /**
     * <pre>
     * Trace Size: 7.9 MB
     * Tracer: FTrace 2.6
     * Event count: -
     * Kernel version: 5.2
     * Trace length: -
     * </pre>
     *
     * An invalid ftrace with bad version header, generated using trace-cmd 2.8.
     * Trace-cmd has bugs that cause bad header issue.
     */
    TEST_2_6_INVALID("/2.6/trace_invalid.dat", 0, 0),

    /**
     * <pre>
     * Trace Size: 7.1 MB
     * Tracer: FTrace 2.6
     * Event count: 0
     * Kernel version: 5.2
     * Trace length: 0s
     * </pre>
     *
     * The file is obtained by running trace-cmd for the ls command: trace-cmd
     * record -e sys_enter_eventfd2 ls. The ls command should be cause an
     * sys_enter_eventfd2 to happen.
     */
    TEST_2_6_EMPTY("/2.6/empty_trace.dat", 0, 0),

    /**
     * <pre>
     * Trace Size: 8 MB
     * Tracer: FTrace 2.6
     * Event count: 2880
     * Kernel version: 5.2
     * Trace length: 1s
     * </pre>
     *
     * This file is an injected trace, the size of the Header Page section of this
     * trace file is injected to make it larger than the size of the file. This
     * trace is modified from {@link FtraceTestTrace#TEST_2_6_MULTIPLE_CPUS}.
     */
    TEST_2_6_BAD_HEADER_PAGE_SECTION_SIZE("/2.6/trace_injected_header.dat", 2880, 1),

    /**
     * <pre>
     * Trace Size: 8 MB
     * Tracer: FTrace 2.6
     * Event count: 2880
     * Kernel version: 5.2
     * Trace length: 1s
     * </pre>
     *
     * This file is an injected trace, the size of the first CPU section of this
     * trace file is injected to make it larger than the size of the file. This
     * trace is modified from {@link FtraceTestTrace#TEST_2_6_MULTIPLE_CPUS}.
     */
    TEST_2_6_BAD_CPU_SECTION_SIZE("/2.6/trace_injected_cpu.dat", 2880, 1);

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
