package org.rest.tester;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Combo;

import java.awt.ScrollPane;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.ScrolledComposite;

public class RestTester {

	protected Shell shlResttester;
	private Text txtServerName;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Text txtUri;
	private Text txtInput;
	private Text txtOutputFolder;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			RestTester window = new RestTester();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlResttester.open();
		shlResttester.layout();
		while (!shlResttester.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlResttester = new Shell();
		shlResttester.setToolTipText("Add more ..");
		shlResttester.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		shlResttester.setSize(450, 371);
		shlResttester.setText("RestTester");
		
		txtServerName = new Text(shlResttester, SWT.BORDER);
		txtServerName.setBounds(121, 10, 279, 30);
		
		Label lblServerName = formToolkit.createLabel(shlResttester, "Server Name :", SWT.NONE);
		lblServerName.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblServerName.setBackground(SWTResourceManager.getColor(245, 245, 245));
		lblServerName.setBounds(33, 13, 82, 18);
		
		ScrolledForm scrldfrmNewScrolledform = formToolkit.createScrolledForm(shlResttester);
		scrldfrmNewScrolledform.setBounds(32, 53, 385, 128);
		formToolkit.paintBordersFor(scrldfrmNewScrolledform);
		scrldfrmNewScrolledform.setText("Rest Services Form");
//		scrldfrmNewScrolledform.setExpandVertical(false);
		scrldfrmNewScrolledform.setAlwaysShowScrollBars(true);
		
		Label lblUri = new Label(scrldfrmNewScrolledform.getBody(), SWT.NONE);
		lblUri.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblUri.setBounds(21, 11, 55, 20);
		formToolkit.adapt(lblUri, true, true);
		lblUri.setText("URI :");
		
		txtUri = new Text(scrldfrmNewScrolledform.getBody(), SWT.BORDER);
		txtUri.setBounds(89, 10, 162, 23);
		formToolkit.adapt(txtUri, true, true);
		
		Combo cmbMethodType = new Combo(scrldfrmNewScrolledform.getBody(), SWT.NONE);
		cmbMethodType.setItems(new String[] {"GET", "POST", "PUT", "DELETE", "OPTION"});
		cmbMethodType.setBounds(267, 10, 91, 23);
		formToolkit.adapt(cmbMethodType);
		formToolkit.paintBordersFor(cmbMethodType);
		cmbMethodType.select(0);
		
		Label lblInput = formToolkit.createLabel(scrldfrmNewScrolledform.getBody(), "Input :", SWT.NONE);
		lblInput.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblInput.setBounds(20, 39, 55, 20);
		
		txtInput = new Text(scrldfrmNewScrolledform.getBody(), SWT.BORDER);
		txtInput.setBounds(89, 39, 162, 25);
		formToolkit.adapt(txtInput, true, true);
		
		Button btnInputFileBrowse = new Button(scrldfrmNewScrolledform.getBody(), SWT.NONE);
		btnInputFileBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				final JFileChooser fc  = new JFileChooser();
				fc.setFileFilter(new FileNameExtensionFilter("Json File", "json","txt"));
				int response = fc.showOpenDialog(null);
				if(response==JFileChooser.APPROVE_OPTION){
					 txtInput.setText(fc.getSelectedFile().toString());
				}
			}
		});
		btnInputFileBrowse.setBounds(267, 39, 91, 25);
		formToolkit.adapt(btnInputFileBrowse, true, true);
		btnInputFileBrowse.setText("Browse..");
		
		Label label = new Label(scrldfrmNewScrolledform.getBody(), SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(0, 76, 368, 2);
		formToolkit.adapt(label, true, true);
		
		txtOutputFolder = new Text(shlResttester, SWT.BORDER);
		txtOutputFolder.setBounds(121, 241, 223, 30);
		formToolkit.adapt(txtOutputFolder, true, true);
		
		Button btnSelectOutDir = new Button(shlResttester, SWT.NONE);
		btnSelectOutDir.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				final JFileChooser fc  = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int response = fc.showOpenDialog(null);
				if(response==JFileChooser.APPROVE_OPTION){
					 txtOutputFolder.setText(fc.getSelectedFile().toString());
				}
			}
		});
		btnSelectOutDir.setBounds(350, 235, 50, 32);
		formToolkit.adapt(btnSelectOutDir, true, true);
		btnSelectOutDir.setText("Select");
		
		Label lblReportFolder = formToolkit.createLabel(shlResttester, "Report Folder ", SWT.NONE);
		lblReportFolder.setBackground(SWTResourceManager.getColor(245, 245, 245));
		lblReportFolder.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblReportFolder.setBounds(33, 242, 88, 21);
		
		Button btnRun = formToolkit.createButton(shlResttester, "Run", SWT.NONE);
		btnRun.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println(txtServerName.getText()+"/"+txtUri.getText());
			}
		});
		btnRun.setBounds(185, 288, 75, 35);
		
		Composite composite = new Composite(shlResttester, SWT.NONE);
		composite.setBounds(33, 176, 367, 47);
		formToolkit.adapt(composite);
		formToolkit.paintBordersFor(composite);
		
		Button btnSave = formToolkit.createButton(composite, "Save", SWT.NONE);
		btnSave.setBounds(87, 10, 75, 25);
		
		Button btnAdd = new Button(composite, SWT.NONE);
		btnAdd.setBounds(229, 10, 28, 25);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Label lblUri = new Label(scrldfrmNewScrolledform.getBody(), SWT.NONE);
				lblUri.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
				lblUri.setBounds(21, 100, 55, 20);
				formToolkit.adapt(lblUri, true, true);
				lblUri.setText("URI :");
				
				txtUri = new Text(scrldfrmNewScrolledform.getBody(), SWT.BORDER);
				txtUri.setBounds(89, 100, 162, 23);
				formToolkit.adapt(txtUri, true, true);
				
				Combo cmbMethodType = new Combo(scrldfrmNewScrolledform.getBody(), SWT.NONE);
				cmbMethodType.setItems(new String[] {"GET", "POST", "PUT", "DELETE", "OPTION"});
				cmbMethodType.setBounds(267, 100, 91, 23);
				formToolkit.adapt(cmbMethodType);
				formToolkit.paintBordersFor(cmbMethodType);
				cmbMethodType.select(0);
				
				Label lblInput = formToolkit.createLabel(scrldfrmNewScrolledform.getBody(), "Input :", SWT.NONE);
				lblInput.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
				lblInput.setBounds(20, 135, 55, 20);
				
				txtInput = new Text(scrldfrmNewScrolledform.getBody(), SWT.BORDER);
				txtInput.setBounds(89, 135, 162, 25);
				formToolkit.adapt(txtInput, true, true);
				
				Button btnInputFileBrowse = new Button(scrldfrmNewScrolledform.getBody(), SWT.NONE);
				btnInputFileBrowse.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						final JFileChooser fc  = new JFileChooser();
						fc.setFileFilter(new FileNameExtensionFilter("Json File", "json","txt"));
						int response = fc.showOpenDialog(null);
						if(response==JFileChooser.APPROVE_OPTION){
							 txtInput.setText(fc.getSelectedFile().toString());
						}
					}
				});
				btnInputFileBrowse.setBounds(267, 135, 91, 25);
				formToolkit.adapt(btnInputFileBrowse, true, true);
				btnInputFileBrowse.setText("Browse..");
				
				Label label = new Label(scrldfrmNewScrolledform.getBody(), SWT.SEPARATOR | SWT.HORIZONTAL);
				label.setBounds(0, 170, 368, 2);
				formToolkit.adapt(label, true, true);
				
				scrldfrmNewScrolledform.setBounds(32, 53, 385, 114);
//				scrldfrmNewScrolledform.setExpandVertical(false);
				scrldfrmNewScrolledform.setAlwaysShowScrollBars(true);
				
				scrldfrmNewScrolledform.pack();
			}
		});
		btnAdd.setToolTipText("Add more ..");
		formToolkit.adapt(btnAdd, true, true);
		btnAdd.setText("+");

	}
}
