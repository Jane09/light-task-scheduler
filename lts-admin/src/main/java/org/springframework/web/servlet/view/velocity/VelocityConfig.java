package org.springframework.web.servlet.view.velocity;

import org.apache.velocity.app.VelocityEngine;

/**
 * @author tb
 * @date 2018/12/21 11:30
 */
public interface VelocityConfig {

    VelocityEngine getVelocityEngine();
}
