/*
 * Copyright 2019 dc-square GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hivemq.persistence;


import com.hivemq.annotations.NotNull;
import com.hivemq.annotations.Nullable;
import io.netty.channel.Channel;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Dominik Obermaier
 */
public class ChannelPersistenceImpl implements ChannelPersistence {

    private final @NotNull Map<String, Channel> channelMap;

    public ChannelPersistenceImpl() {
        channelMap = new ConcurrentHashMap<>();
    }

    @Nullable
    @Override
    public Channel get(final @NotNull String clientId) {

        return channelMap.get(clientId);
    }

    @Override
    public void persist(final @NotNull String clientId, final @NotNull Channel value) {
        channelMap.put(clientId, value);
    }

    @Nullable
    @Override
    public Channel remove(final @NotNull String clientId) {
        return channelMap.remove(clientId);
    }

    @Override
    public long size() {
        return channelMap.size();
    }

    @NotNull
    @Override
    public Set<Map.Entry<String, Channel>> entries() {
        return channelMap.entrySet();
    }

}