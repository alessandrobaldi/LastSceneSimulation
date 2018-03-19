/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.study.scenary.entities;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("4d")
/**
 *
 * @author alessandro
 */
public class agentCall {
   private House calledAgent;

public House getCalledAgent() {
	return calledAgent;
}

public void setCalledAgent(House calledAgent) {
	this.calledAgent = calledAgent;
}

   
}
