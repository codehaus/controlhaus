/*
 * This file illustrates one way to customize floating popup portlets.  However, it is not enabled by default.
 * To enable this javascript functionality, uncomment the call to "initPortletFloatButtons()" in skin.js.
 */

function initPortletFloatButtons()
{
    var links = document.getElementsByTagName("a");

    for (var i = 0; i < links.length; i++)
    {
        if (links[i].className && links[i].className == "bea-portal-button-float")
        {
            initPortletFloatButton(links[i]);
        }
    }
}

function initPortletFloatButton(link)
{
    link.onclick = floatPortlet;
}

function floatPortlet(event)
{
    var link = getEventSource(getEvent(event));
    var href = "about:blank";
    var target = "_blank";

    if (link)
    {
        if (link.href)
        {
            href = link.href;
        }

        if (link.target)
        {
            target = link.target;
        }
    }

    // This is the interesting line for customization purposes:
    window.open(href, target, "toolbar = no, width = 320, height = 240, directories = no, status = no, scrollbars = yes, resize = yes, menubar = no");
    // Returning false keeps the browser from submitting this button click to the server.
    return false;
}
