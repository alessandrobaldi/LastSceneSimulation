/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.study.scenary.entities;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("1d")
/**
 *
 * @author alessandro
 */
public class mosquitoFlown {
   private Mosquito migrated;

public Mosquito getMigrated() {
	return migrated;
}

public void setMigrated(Mosquito migrated) {
	this.migrated = migrated;
}
   
}
