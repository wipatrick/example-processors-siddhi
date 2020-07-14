/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */


package org.apache.streampipes.processors.siddhi.time;

import org.apache.streampipes.wrapper.siddhi.engine.SiddhiDebugCallback;
import org.apache.streampipes.wrapper.siddhi.engine.SiddhiEventEngine;

import java.util.List;

public class TimeExample extends SiddhiEventEngine<TimeExampleParameters> {

    public TimeExample() {
        super();
    }

    public TimeExample(SiddhiDebugCallback callback) {
        super(callback);
    }

    @Override
    protected String fromStatement(List<String> inputStreamNames, TimeExampleParameters params) {
        String timestampProperty = prepareName(params.getTimestampProperty());
        int windowLength = params.getWindowLength();

        // e.g. Forward events based on windows
        //
        //
        //return "from " + inputStreamNames.get(0) + "#window.lengthBatch(10)";
        return "from " + inputStreamNames.get(0) + "#window.externalTimeBatch(" + timestampProperty + ", " + windowLength + " sec)";
    }

    @Override
    protected String selectStatement(TimeExampleParameters params) {
        return getCustomOutputSelectStatement(params.getGraph());
    }

    /**
     * Get timestamp from event fields and pass to SiddhiEventEngine
     *
     * @param params
     * @return
     */
    @Override
    protected String setTimestamp(TimeExampleParameters params) {
        return params.getTimestampProperty();
    }
}
