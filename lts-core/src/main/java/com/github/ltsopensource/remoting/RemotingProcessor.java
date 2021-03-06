package com.github.ltsopensource.remoting;

import com.github.ltsopensource.remoting.exception.RemotingCommandException;
import com.github.ltsopensource.remoting.protocol.RemotingCommand;

/**
 * 接收请求处理器，服务器与客户端通用
 * @author hg
 */
public interface RemotingProcessor {

    RemotingCommand processRequest(Channel channel, RemotingCommand request)
            throws RemotingCommandException;
}
