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

import com.bea.jsptools.portal.placement.NavigablePlacement;
import com.bea.jsptools.portal.placement.Placement;

public class PagePlacement extends Placement implements NavigablePlacement
{
    public PagePlacement(int id, int parentId, int containerId, int position)
    {
        super(id, parentId, containerId, position);
    }

    public PagePlacement(String id, String parentId, String containerId, String position)
    {
        super(id, parentId, containerId, position);
    }

}
