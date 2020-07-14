<!--
  ~ Licensed to the Apache Software Foundation (ASF) under one or more
  ~ contributor license agreements.  See the NOTICE file distributed with
  ~ this work for additional information regarding copyright ownership.
  ~ The ASF licenses this file to You under the Apache License, Version 2.0
  ~ (the "License"); you may not use this file except in compliance with
  ~ the License.  You may obtain a copy of the License at
  ~
  ~    http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing, software
  ~ distributed under the License is distributed on an "AS IS" BASIS,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  ~ See the License for the specific language governing permissions and
  ~ limitations under the License.
  ~
  -->

## Simple Time Window Example

<p align="center"> 
    <img src="icon.png" width="150px;" class="pe-image-documentation"/>
</p>

***

## Description
Dummy time window processor using Siddhi's window semantics.

```
// external event time batch window
"from inputStreamNames#window.externalTimeBatch(s0timestamp, 10 sec)"
select *
```

***

## Required input
Timestamp field and time window length.
***

## Configuration

### Timestamp Field
Specifies the timestamp field.

### Window Length
Specifies the window length in seconds.

## Output
The processor outputs events according to the user-defined time window.