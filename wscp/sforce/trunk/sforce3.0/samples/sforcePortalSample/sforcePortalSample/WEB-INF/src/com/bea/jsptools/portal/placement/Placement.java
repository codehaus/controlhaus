/*   Copyright 2004 Salesforce.com
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.bea.jsptools.portal.placement;

public class Placement
{
    private int id;
    private int parentId;
    private int containerId;
    private int position; 
    
    public Placement(int id, int parentId, int containerId, int position)
    {
        this.id = id;
        this.parentId = parentId;
        this.containerId = containerId;
        this.position = position;
    }

    public Placement(String id, String parentId, String containerId, String position)
    {
        this.id = Integer.parseInt(id);
        this.parentId = Integer.parseInt(parentId);
        this.containerId = Integer.parseInt(containerId);
        this.position = Integer.parseInt(position);
    }

    public int getId()
    {
        return this.id;
    }

    public int getParentId()
    {
        return this.parentId;
    }

    public int getContainerId()
    {
        return this.containerId;
    }
    
    public int getPosition()
    {
        return this.position;
    }
    
}
