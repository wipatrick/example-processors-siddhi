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

import org.apache.streampipes.model.DataProcessorType;
import org.apache.streampipes.model.graph.DataProcessorDescription;
import org.apache.streampipes.model.graph.DataProcessorInvocation;
import org.apache.streampipes.model.schema.PropertyScope;
import org.apache.streampipes.sdk.builder.ProcessingElementBuilder;
import org.apache.streampipes.sdk.builder.StreamRequirementsBuilder;
import org.apache.streampipes.sdk.extractor.ProcessingElementParameterExtractor;
import org.apache.streampipes.sdk.helpers.*;
import org.apache.streampipes.sdk.utils.Assets;
import org.apache.streampipes.wrapper.standalone.ConfiguredEventProcessor;
import org.apache.streampipes.wrapper.standalone.declarer.StandaloneEventProcessingDeclarer;

public class TimeExampleController extends StandaloneEventProcessingDeclarer<TimeExampleParameters> {

	private static final String TIMESTAMP_FIELD = "timestamp";
	private static final String WINDOW_LENGTH = "window-length";

	@Override
	public DataProcessorDescription declareModel() {
		return ProcessingElementBuilder.create("org.apache.streampipes.processors.siddhi.time")
				.category(DataProcessorType.FILTER)
				.withLocales(Locales.EN)
				.withAssets(Assets.DOCUMENTATION)
				.requiredStream(StreamRequirementsBuilder
						.create()
						.requiredPropertyWithUnaryMapping(EpRequirements.timestampReq(),
								Labels.withId(TIMESTAMP_FIELD), PropertyScope.HEADER_PROPERTY)
						.build())
				.requiredIntegerParameter(Labels.withId(WINDOW_LENGTH))
				.outputStrategy(OutputStrategies.custom())
				.build();
	}

	@Override
	public ConfiguredEventProcessor<TimeExampleParameters> onInvocation(DataProcessorInvocation graph, ProcessingElementParameterExtractor extractor) {

		String timestampProperty = extractor.mappingPropertyValue(TIMESTAMP_FIELD);
		int windowLength = extractor.singleValueParameter(WINDOW_LENGTH, Integer.class);

		TimeExampleParameters staticParam = new TimeExampleParameters(graph, timestampProperty, windowLength);

		return new ConfiguredEventProcessor<>(staticParam, TimeExample::new);
	}

}
