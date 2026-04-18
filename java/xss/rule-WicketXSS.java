// License: LGPL-3.0 License (c) find-sec-bugs
package xss;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Radio;
//import org.apache.wicket.extensions.markup.html.form.select.SelectOptions.SimpleSelectOption;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.Check;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.model.util.ListModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import org.apache.wicket.markup.html.form.Form;

class WicketXSS extends WebPage {
    public void XssWicketExamplePage(PageParameters pageParameters) {
        // ruleid: java_xss_rule-WicketXSS
        add(new Label("test").setEscapeModelStrings(false));
    }

    // http://www.java2s.com/example/java-src/pkg/org/jabylon/rest/ui/wicket/components/customfeedbackpanel-edea7.html
    protected Component newMessageDisplayComponent(String id, String message) {
        MultiLineLabel label = new MultiLineLabel(id, message);
        // ruleid: java_xss_rule-WicketXSS
        label.setEscapeModelStrings(false);
        return label;
    }

    public void setText(String txt, Component c) {

        if (txt == null || txt.trim().length() == 0) {
            c.setVisible(false);
        } else {
            c.setDefaultModelObject(txt);
            c.setVisible(true);
            // ruleid: java_xss_rule-WicketXSS
            c.setEscapeModelStrings(false);
        }
    }

    protected void newLabel(Label lab) {
        final Component label = lab;
        // ruleid: java_xss_rule-WicketXSS
        label.setEscapeModelStrings(false);

        // https://github.com/apache/wicket/blob/e9461b0d115a7dbf4992596823521f6e038817d9/wicket-user-guide/src/main/asciidoc/forms2/forms2_3.adoc#L134
        // ruleid: java_xss_rule-WicketXSS
        add(new FeedbackPanel("feedbackMessage").setEscapeModelStrings(false));

    }

    public void advancedSearchPage(String input) {

        Form<?> form = new Form<>("form");
        // Options for the dropdown
        List<String> options = Arrays.asList("Option 1 " + input, "Option 2 " + input, "Option 3 " + input);

        // Model for the selected value
        Model<String> selectedOption = new Model<>("Option 1 " + input);

        // Create a DropDownChoice and add it to the form
        DropDownChoice<String> dropdown = new DropDownChoice<>("dropdown", selectedOption, options);
        // ruleid: java_xss_rule-WicketXSS
        dropdown.setEscapeModelStrings(false);
        form.add(dropdown);

        add(form);

    }

    // https://www.tabnine.com/code/java/methods/org.apache.wicket.markup.html.form.Radio/setEscapeModelStrings
    private String getFormComponentValue(final Radio<?> formComponent) {
        boolean oldEscape = formComponent.getEscapeModelStrings();
        // ruleid: java_xss_rule-WicketXSS
        formComponent.setEscapeModelStrings(false);
        String val = formComponent.getValue();
        formComponent.setEscapeModelStrings(oldEscape);
        return val;
    }

    // https://www.tabnine.com/code/java/methods/org.apache.wicket.markup.html.form.Check/setEscapeModelStrings
    private String getFormComponentValue2(final Check<?> formComponent) {
        boolean oldEscape = formComponent.getEscapeModelStrings();
        // ruleid: java_xss_rule-WicketXSS
        formComponent.setEscapeModelStrings(false);
        String val = formComponent.getValue();
        formComponent.setEscapeModelStrings(oldEscape);
        return val;
    }
}

class MyPages extends WebPage {

    private ModalWindow modalWindow;

    public MyPages() {
        // Create a ModalWindow and set its title
        modalWindow = new ModalWindow("modalWindow");
        modalWindow.setTitle("Potentially Unsafe Title");

        // Explicitly disable escaping of the model strings (title)
        // for CVE-2015-5347/
        // ruleid: java_xss_rule-WicketXSS
        modalWindow.setEscapeModelStrings(false);
        add(modalWindow);
    }
}

class MyPage extends WebPage {

    public MyPage(List<String> userGeneratedOptions, String input) {

        Form<Void> form = new Form<>("form");

        Model<String> selectedOption = Model.of("");

        RadioGroup<String> radioGroup = new RadioGroup<>("radioGroup", selectedOption);
        // for CVE-2015-7520
        // ruleid: java_xss_rule-WicketXSS
        radioGroup.setEscapeModelStrings(false);

        for (String option : userGeneratedOptions) {
            radioGroup.add(new Radio<>("radio" + option, Model.of(option)));
        }

        form.add(radioGroup);

        // https://nightlies.apache.org/wicket/apidocs/8.x/org/apache/wicket/markup/html/form/CheckBoxMultipleChoice.html
        List SITES = Arrays.asList(new String[] { "The Server Side", "Java Lobby", input });
        // for CVE-2015-7520
        // ruleid: java_xss_rule-WicketXSS
        form.add(new CheckBoxMultipleChoice("site", SITES).setEscapeModelStrings(false));
    }
}
