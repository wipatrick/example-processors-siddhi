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

package org.apache.streampipes.config;

import org.apache.streampipes.config.SpConfig;
import org.apache.streampipes.container.model.PeConfig;

import static org.apache.streampipes.config.ConfigKeys.*;

public enum Config implements PeConfig {

  INSTANCE;

  private SpConfig config;

  public final static String serverUrl;

  private final static String SERVICE_ID= "pe/org.apache.streampipes.processors.siddhi";

  Config() {
    config = SpConfig.getSpConfig("pe/org.apache.streampipes.processors.siddhi");

    config.register(HOST, "streampipes-processors-siddhi", "Hostname for the pe sinks");
    config.register(PORT, 8090, "Port for the pe sinks");

    config.register(ICON_HOST, "backend", "Hostname for the icon host");
    config.register(ICON_PORT, 80, "Port for the icons in nginx");

    config.register(SERVICE_NAME, "Example Processors Siddhi", "The name of the service");
  }

  static {
    serverUrl = Config.INSTANCE.getHost() + ":" + Config.INSTANCE.getPort();
  }

  public String getHost() {
    return config.getString(HOST);
  }

  public int getPort() {
    return config.getInteger(PORT);
  }

  @Override
  public String getId() {
    return SERVICE_ID;
  }

  @Override
  public String getName() {
    return config.getString(SERVICE_NAME);
  }

}
