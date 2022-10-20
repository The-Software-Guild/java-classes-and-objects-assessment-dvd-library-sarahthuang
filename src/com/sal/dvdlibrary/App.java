package com.sal.dvdlibrary;

import com.sal.dvdlibrary.controller.DvdLibraryController;
import com.sal.dvdlibrary.dao.DvdLibraryDaoFileImpl;
import com.sal.dvdlibrary.dao.dvdLibraryDao;
import com.sal.dvdlibrary.ui.DvdLibraryView;
import com.sal.dvdlibrary.ui.UserIO;
import com.sal.dvdlibrary.ui.UserIOConsoleImpl;

public class App {
    public static void main(String[] args) {
        /*
        Instantiate the UserIO, DvdLibraryView,dvdLibraryDao,  DvdLibraryController class and call the run method
         */
        UserIO myIo = new UserIOConsoleImpl();
        DvdLibraryView myView = new DvdLibraryView(myIo);
        dvdLibraryDao myDao = new DvdLibraryDaoFileImpl();
        DvdLibraryController controller =
                new DvdLibraryController(myView, myDao);
        controller.run();
    }
}
