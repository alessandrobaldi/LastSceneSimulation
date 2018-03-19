/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.study.scenary.entities;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("15d")
/**
 *
 * @author alessandro
 */
public class Rained {
   private House returningfocus;

public House getReturningfocus() {
	return returningfocus;
}

public void setReturningfocus(House returningfocus) {
	this.returningfocus = returningfocus;
}

   
}
