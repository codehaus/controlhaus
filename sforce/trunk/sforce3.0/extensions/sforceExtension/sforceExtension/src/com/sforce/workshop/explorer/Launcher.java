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

package com.sforce.workshop.explorer; 

import java.io.*;

public class Launcher {
  public Launcher() {
  }
  public static void displayURL(String url)
    {
      boolean windows = isWindowsPlatform();
        String cmd = null;
        try
        {
          if (windows)
          {
            // cmd = 'rundll32 url.dll,FileProtocolHandler http://...'
            cmd = WIN_PATH + " " + WIN_FLAG + " " + url;
            Process p = Runtime.getRuntime().exec(cmd);
          }
          else
          {
            // Under Unix, Netscape has to be running for the "-remote"
            // command to work.  So, we try sending the command and
            // check for an exit value.  If the exit command is 0,
            // it worked, otherwise we need to start the browser.
            // cmd = 'netscape -remote openURL(http://www.javaworld.com)'
            cmd = UNIX_PATH + " " + UNIX_FLAG + "(" + url + ")";
            Process p = Runtime.getRuntime().exec(cmd);
            try
            {
              // wait for exit code -- if it's 0, command worked,
              // otherwise we need to start the browser up.
              int exitCode = p.waitFor();
              if (exitCode != 0)
              {
                // Command failed, start up the browser
                // cmd = 'netscape http://www.javaworld.com'
                cmd = UNIX_PATH + " "  + url;
                p = Runtime.getRuntime().exec(cmd);
              }
            }
            catch(InterruptedException x)
            {
              //System.err.println("Error, cmd='" +cmd + "'");
              //System.err.println("Caught: " + x);
            }
          }
        }
        catch(IOException x)
        {
          // couldn't exec browser
          //System.err.println("Can't get browser, command=" + cmd);
          //System.err.println("Caught: " + x);
        }
      }
      /**
       * Try to determine whether this application is running under
  Windows
       * or some other platform by examing the "os.name" property.
       * @return true if this application is running under a Windows OS
       */
      public static boolean isWindowsPlatform()
      {
        String os = System.getProperty("os.name");
        if ( os != null && os.startsWith(WIN_ID))
          return true;
        else
          return false;
      }
      // Used to identify the windows platform.
      private static final String WIN_ID = "Windows";
      // The default system browser under windows.
      private static final String WIN_PATH = "rundll32";
      // The flag to display a url.
      private static final String WIN_FLAG =  "url.dll,FileProtocolHandler";
      // The default browser under unix.
      private static final String UNIX_PATH = "netscape";
      // The flag to display a url.
      private static final String UNIX_FLAG = "-remote openURL";
}
