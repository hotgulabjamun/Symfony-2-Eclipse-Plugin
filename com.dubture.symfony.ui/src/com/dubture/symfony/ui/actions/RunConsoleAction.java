package com.dubture.symfony.ui.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jface.action.IAction;
import org.eclipse.php.internal.debug.core.preferences.PHPexeItem;
import org.eclipse.php.internal.debug.core.preferences.PHPexes;
import org.phpsrc.eclipse.pti.core.launching.PHPToolLauncher;
import org.phpsrc.eclipse.pti.ui.actions.ResourceAction;

import com.dubture.symfony.ui.SymfonyUiPlugin;

@SuppressWarnings("restriction")
public class RunConsoleAction extends ResourceAction {

	public final static QualifiedName QUALIFIED_NAME = new QualifiedName(SymfonyUiPlugin.PLUGIN_ID,
			"console");	

	@Override
	public void run(IAction action)
	{	
		IResource[] resources = getSelectedResources();
		if (resources.length > 0) {

			if (resources[0] instanceof IProject) {
				
				IProject project = (IProject) resources[0];			
				IFile file = project.getFile(new Path("app/console.php"));				
				
				String args = " eclipse:services";
				PHPToolLauncher launcher = new PHPToolLauncher(QUALIFIED_NAME, getPHPExe(), file.getRawLocation(), args);	
				launcher.setPrintOuput(true);
				String output = launcher.launch(file);
				
				System.err.println("output");				
				System.err.println(output);
				
				
			}
		}		
	}
	
	public PHPexeItem getPHPExe() {
		
		PHPexeItem[] items = PHPexes.getInstance().getAllItems();
		for (PHPexeItem item : items) {
			if (item.getName().equals("CLI"))
				return item;
		}
		
		return null;
		
	}
}
