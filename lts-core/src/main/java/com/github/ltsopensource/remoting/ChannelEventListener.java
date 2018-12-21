package com.github.ltsopensource.remoting;

/**
 * 监听Channel的事件，包括连接断开、连接建立、连接异常，传送这些事件到应用层
 */
public interface ChannelEventListener {

    void onChannelConnect(final String remoteAddr, final Channel channel);

    void onChannelClose(final String remoteAddr, final Channel channel);

    void onChannelException(final String remoteAddr, final Channel channel);

    void onChannelIdle(IdleState idleState, final String remoteAddr, final Channel channel);

}
