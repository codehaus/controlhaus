/*   Copyright 2004 BEA Systems, Inc.
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


package org.controlhaus.im.jabber.impl;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.controlhaus.im.jabber.impl.Contract;
import org.controlhaus.im.jabber.impl.JabberAccount;
import org.controlhaus.im.jabber.impl.JabberInstantMessenger;

public class JabberInstantMessengerImpl implements JabberInstantMessenger {

    public void instantMessage(String toUser, String message, JabberAccount jabberAccount) throws XMPPException {

        Contract.enforce(toUser != null && !toUser.equals(""), "toUser");
        Contract.enforce(message != null, "message");
        Contract.enforce(jabberAccount != null, "jabberAccount");
        Contract.enforce(jabberAccount.getUserid() != null && !jabberAccount.getUserid().equals(""), "jabberAccount");
        Contract.enforce(jabberAccount.getPassword() != null && !jabberAccount.getPassword().equals(""), "jabberAccount");
        Contract.enforce(jabberAccount.getServername() != null && !jabberAccount.getServername().equals(""), "jabberAccount");


        XMPPConnection connection = new XMPPConnection(jabberAccount.getServername());
        connection.login(jabberAccount.getUserid(), jabberAccount.getPassword());
        connection.createChat(toUser).sendMessage(message);
    }
}
