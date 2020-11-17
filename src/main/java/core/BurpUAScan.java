package core;

import burp.IBurpExtender;
import burp.IBurpExtenderCallbacks;
import burp.ITab;
import common.Constants;
import common.PrintUtil;
import listener.HttpListener;
import ui.UAScan;

import javax.swing.*;
import java.awt.*;

public class BurpUAScan implements IBurpExtender, ITab {

    private IBurpExtenderCallbacks callbacks;
    private JPanel jPanelMain;

    public BurpUAScan() {
    }

    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
        this.callbacks = callbacks;
        callbacks.setExtensionName(Constants.EXTENSION_NAME);
        callbacks.registerHttpListener(new HttpListener(callbacks));
        PrintUtil.print(callbacks, Constants.INTRODUCTION);
        buildUI();
    }

    private void buildUI() {
        SwingUtilities.invokeLater(() -> {
            jPanelMain = new JPanel();
            UAScan ui = new UAScan();
            JComponent component = ui.$$$getRootComponent$$$();
            jPanelMain.add(component);
            callbacks.customizeUiComponent(jPanelMain);
            callbacks.addSuiteTab(BurpUAScan.this);
        });
    }

    @Override
    public String getTabCaption() {
        return Constants.EXTENSION_NAME;
    }

    @Override
    public Component getUiComponent() {
        return jPanelMain;
    }
}
