/*******************************************************************************
 * Copyright (c) 2014 hangum.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     hangum - initial API and implementation
 ******************************************************************************/
package com.hangum.tadpole.monitoring.core.actions.monitoring;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

import com.hangum.tadpole.commons.exception.dialog.ExceptionDetailsErrorDialog;
import com.hangum.tadpole.monitoring.core.Activator;
import com.hangum.tadpole.monitoring.core.Messages;
import com.hangum.tadpole.monitoring.core.editors.monitoring.manage.MonitoringManagerEditor;
import com.hangum.tadpole.monitoring.core.editors.monitoring.manage.MonitoringManagerInput;
import com.swtdesigner.ResourceManager;

/**
 * Monitoring manage open action
 * 
 * @author hangum
 *
 */
public class MonitoringManageAction extends Action implements ISelectionListener, IWorkbenchAction {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MonitoringManageAction.class);
	private final static String ID = "com.hangum.tadpole.monitoring.core.actions.monitoring.actions.global.MonitoringManageAction"; //$NON-NLS-1$
	
	private final IWorkbenchWindow window;
	private IStructuredSelection iss;
	
	public MonitoringManageAction(IWorkbenchWindow window) {
		this.window = window;
		
		setId(ID);
		setText("Monitoring Manage");
		setToolTipText("Monitoring Manage");
		setImageDescriptor(ResourceManager.getPluginImageDescriptor(Activator.PLUGIN_ID, "resources/icons/monitoring.png"));
		setEnabled(true);
	}
	
	@Override
	public void run() {
		try {
			MonitoringManagerInput monitoringManageInput = new MonitoringManagerInput();
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(monitoringManageInput, MonitoringManagerEditor.ID);
		} catch (PartInitException e) {
			logger.error("Monitoring Manage editor open", e); //$NON-NLS-1$
			
			Status errStatus = new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(), e); //$NON-NLS-1$
			ExceptionDetailsErrorDialog.openError(null, Messages.get().Error, "Monitoring Manage editor", errStatus); //$NON-NLS-1$
		}
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
	}

	@Override
	public void dispose() {
	}

}
