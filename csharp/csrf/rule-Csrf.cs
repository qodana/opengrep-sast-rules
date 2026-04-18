// License: LGPL-3.0 License (c) security-code-scan
using System.Web.Mvc;

class Csrf
{
    // ruleid: csharp_csrf_rule-Csrf
    [HttpPost]
    public ActionResult ControllerMethod1(string input)
    {
        return null;
    }

    // ruleid: csharp_csrf_rule-Csrf
    [HttpDelete]
    public ActionResult ControllerMethod2(string input)
    {
        return null;
    }

    // ruleid: csharp_csrf_rule-Csrf
    [HttpPatch]
    public ActionResult ControllerMethod3(string input)
    {
        return null;
    }

    // ruleid: csharp_csrf_rule-Csrf
    [HttpPut]
    public ActionResult ControllerMethod4(string input)
    {
        return null;
    }

    // ok: csharp_csrf_rule-Csrf
    [HttpPost]
    [ValidateAntiForgeryToken]
    public ActionResult ControllerMethod5(string input)
    {
        return null;
    }

    // ok: csharp_csrf_rule-Csrf
    [HttpDelete]
    [ValidateAntiForgeryToken]
    public ActionResult ControllerMethod6(string input)
    {
        return null;
    }

    // ok: csharp_csrf_rule-Csrf
    [HttpPatch]
    [ValidateAntiForgeryToken]
    public ActionResult ControllerMethod7(string input)
    {
        return null;
    }

    // ok: csharp_csrf_rule-Csrf
    [HttpPut]
    [ValidateAntiForgeryToken]
    public ActionResult ControllerMethod8(string input)
    {
        return null;
    }
}

[AutoValidateAntiforgeryToken]
class CsrfWithAutoValidate
{
    // ok: csharp_csrf_rule-Csrf
    [HttpPost]
    public ActionResult ControllerMethod1(string input)
    {
        return null;
    }

    // ok: csharp_csrf_rule-Csrf
    [HttpDelete]
    public ActionResult ControllerMethod2(string input)
    {
        return null;
    }

    // ok: csharp_csrf_rule-Csrf
    [HttpPatch]
    public ActionResult ControllerMethod3(string input)
    {
        return null;
    }

    // ok: csharp_csrf_rule-Csrf
    [HttpPut]
    public ActionResult ControllerMethod4(string input)
    {
        return null;
    }

    // ok: csharp_csrf_rule-Csrf
    [HttpPost]
    [ValidateAntiForgeryToken]
    public ActionResult ControllerMethod5(string input)
    {
        return null;
    }

    // ok: csharp_csrf_rule-Csrf
    [HttpDelete]
    [ValidateAntiForgeryToken]
    public ActionResult ControllerMethod6(string input)
    {
        return null;
    }

    // ok: csharp_csrf_rule-Csrf
    [HttpPatch]
    [ValidateAntiForgeryToken]
    public ActionResult ControllerMethod7(string input)
    {
        return null;
    }

    // ok: csharp_csrf_rule-Csrf
    [HttpPut]
    [ValidateAntiForgeryToken]
    public ActionResult ControllerMethod8(string input)
    {
        return null;
    }
}

[ValidateAntiForgeryToken]
class CsrfWithAutoValidate
{
    // ok: csharp_csrf_rule-Csrf
    [HttpPost]
    public ActionResult ControllerMethod1(string input)
    {
        return null;
    }

    // ok: csharp_csrf_rule-Csrf
    [HttpDelete]
    public ActionResult ControllerMethod2(string input)
    {
        return null;
    }

    // ok: csharp_csrf_rule-Csrf
    [HttpPatch]
    public ActionResult ControllerMethod3(string input)
    {
        return null;
    }

    // ok: csharp_csrf_rule-Csrf
    [HttpPut]
    public ActionResult ControllerMethod4(string input)
    {
        return null;
    }

    // ok: csharp_csrf_rule-Csrf
    [HttpPost]
    [ValidateAntiForgeryToken]
    public ActionResult ControllerMethod5(string input)
    {
        return null;
    }

    // ok: csharp_csrf_rule-Csrf
    [HttpDelete]
    [ValidateAntiForgeryToken]
    public ActionResult ControllerMethod6(string input)
    {
        return null;
    }

    // ok: csharp_csrf_rule-Csrf
    [HttpPatch]
    [ValidateAntiForgeryToken]
    public ActionResult ControllerMethod7(string input)
    {
        return null;
    }

    // ok: csharp_csrf_rule-Csrf
    [HttpPut]
    [ValidateAntiForgeryToken]
    public ActionResult ControllerMethod8(string input)
    {
        return null;
    }
}
