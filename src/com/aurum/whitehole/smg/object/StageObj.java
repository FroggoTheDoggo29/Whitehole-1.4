/*
    © 2012 - 2017 - Whitehole Team

    Whitehole is free software: you can redistribute it and/or modify it under
    the terms of the GNU General Public License as published by the Free
    Software Foundation, either version 3 of the License, or (at your option)
    any later version.

    Whitehole is distributed in the hope that it will be useful, but WITHOUT ANY 
    WARRANTY; See the GNU General Public License for more details.

    You should have received a copy of the GNU General Public License along 
    with Whitehole. If not, see http://www.gnu.org/licenses/.
*/

package com.aurum.whitehole.smg.object;

import com.aurum.whitehole.swing.PropertyGrid;
import com.aurum.whitehole.rendering.GLRenderer.RenderInfo;
import com.aurum.whitehole.smg.Bcsv;
import com.aurum.whitehole.smg.ZoneArchive;
import com.aurum.whitehole.vectors.Vector3;

public class StageObj extends AbstractObj {
    public StageObj(ZoneArchive zone, String filepath, Bcsv.Entry entry) {
        this.zone = zone;
        String[] stuff = filepath.split("/");
        directory = stuff[0];
        layer = stuff[1].toLowerCase();
        file = stuff[2];
        
        data = entry;
        
        name = (String)data.get("name");
        renderer = null;
        uniqueID = -1;
        
        position = new Vector3((float)data.get("pos_x"), (float)data.get("pos_y"), (float)data.get("pos_z"));
        rotation = new Vector3((float)data.get("dir_z"), (float)data.get("dir_y"), (float)data.get("dir_x"));
        scale = new Vector3(1,1,1);
    }
    
    public StageObj(ZoneArchive zone, String filepath, int game, Vector3 pos) {
        this.zone = zone;
        String[] stuff = filepath.split("/");
        directory = stuff[0];
        layer = stuff[1].toLowerCase();
        file = stuff[2];
        
        data = new Bcsv.Entry();
        
        name = "";
        renderer = null;
        uniqueID = -1;
        
        position = pos;
        rotation = new Vector3(0f, 0f, 0f);
        scale = new Vector3(1f, 1f, 1f);
        
        data.put("name", name);
        data.put("l_id", 0);
        data.put("pos_x", position.x); data.put("pos_y", position.y); data.put("pos_z", position.z);
        data.put("dir_x", rotation.x); data.put("dir_y", rotation.y); data.put("dir_z", rotation.z);
    }
    
    @Override
    public void save() {
        data.put("name", name);
        data.put("pos_x", position.x); data.put("pos_y", position.y); data.put("pos_z", position.z);
        data.put("dir_x", rotation.x); data.put("dir_y", rotation.y); data.put("dir_z", rotation.z);
    }
    
    @Override
    public void render(RenderInfo info) {}
    
    @Override
    public void getProperties(PropertyGrid panel) {
        panel.addCategory("obj_rendering", "Rendering");
        panel.addField("pos_x", "X position", "float", null, position.x, "Default");
        panel.addField("pos_y", "Y position", "float", null, position.y, "Default");
        panel.addField("pos_z", "Z position", "float", null, position.z, "Default");
        panel.addField("dir_x", "X rotation", "float", null, rotation.x, "Default");
        panel.addField("dir_y", "Y rotation", "float", null, rotation.y, "Default");
        panel.addField("dir_z", "Z rotation", "float", null, rotation.z, "Default");
        
        panel.addCategory("obj_settings", "Settings");
        panel.addField("l_id", "ID", "int", null, data.get("l_id"), "Default"); 
    }
    
    @Override
    public String toString() {
        String l = layer.equals("common") ? "Common" : "Layer"+layer.substring(5).toUpperCase();
        return name + " [" + l + "]";
    }
}
