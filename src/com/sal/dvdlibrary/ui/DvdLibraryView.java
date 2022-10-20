package com.sal.dvdlibrary.ui;
import com.sal.dvdlibrary.dto.DvD;
import java.util.List;

public class DvdLibraryView {
    private UserIO io;

    public DvdLibraryView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("DVD Library Main Menu Selection**********");
        io.print("1: Create a DVD record");
        io.print("2: List all DVD records");
        io.print("3: View one DVD record");
        io.print("4: Remove a DVD record");
        io.print("5: Edit a DVD record");
        io.print("6: Exit");

        return io.readInt("Please type a number (1 to 6) to select from the menu", 1, 6);
    }
    public int printEditMenuAndGetSelection() {
        //implement
        io.print("Edit Menu Selection");
        io.print("1: Edit release date");
        io.print("2: Edit MPAA");
        io.print("3: Edit director name");
        io.print("4: Edit user rating");
        io.print("5: Edit studio name");
        io.print("6: Exit");

        return io.readInt("Please select an option from the menu", 1, 6);
    }
    /*
     This method prompts the user for dvd ID, First Name, Last Name, and Cohort,
    gathers this information, creates a new dvd object, and returns it to the caller.
     */
    public DvD getNewDvDInfo() {
        String title = io.readString("Please enter DVD's title");
        String releaseDate = io.readString("Please enter DVD's release date");
        String MPAA = io.readString("Please enter DVD's MPAA");
        String director = io.readString("Please enter DVD's director");
        String studio = io.readString("Please enter DVD's studio");
        String userRating = io.readString("Please enter DVD's user rating");
        DvD dvd = new DvD(title);
        dvd.setReleaseDate(releaseDate);
        dvd.setMPAA(MPAA);
        dvd.setDirectorsName(director);
        dvd.setStudio(studio);
        dvd.setUserRating(userRating);
        return dvd;
    }

    /*
    This method simply displays a banner or heading to the UI indicating that
    the next interactions on the screen will be for creating a new dvd
     */
    public void displayCreateDvDBanner() {
        io.print("=== Create DvD ===");
    }

    /*
    The second method displays a message that the new dvd was successfully created
    and waits for the user to hit Enter to continue
     */
    public void displayCreateSuccessBanner() {
        io.readString("DvD successfully created.  Please hit enter to continue");
    }

    /*
    A method that takes a list of DVD objects as a parameter and displays the information for each Dvd to the screen.
     */
    public void displayDvdList(List<DvD> dvdList) {
        for (DvD dvd : dvdList) {
            String dvdInfo = String.format("Title: %s\n" +
                            "Release Date: %s\n" +
                            "MPAA: %s\n" +
                            "Director: %s\n" +
                            "Studio: %s\n" +
                            "User rating: %s\n",
                    dvd.getDvDTitle(),
                    dvd.getReleaseDate(),
                    dvd.getMPAA(),
                    dvd.getDirectorsName(),
                    dvd.getStudio(),
                    dvd.getUserRating());
            io.print(dvdInfo);
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All Dvds ===");
    }

    /*
    Shows the dvd banner
     */
    public void displayDisplayDvdBanner() {
        io.print("=== Display Dvd ===");
    }

    /*
    Get the dvd title to display information
     */
    public String getDvdTitleChoice() {
        return io.readString("Please enter the dvd title.");
    }

    /*
    Displays the dvd information
     */
    public void displayDvd(DvD dvd) {
        if (dvd != null) {
            io.print("Title: " + dvd.getDvDTitle());
            io.print("Release Date: " + dvd.getReleaseDate());
            io.print("MPAA: " + dvd.getMPAA());
            io.print("Director: " + dvd.getDirectorsName());
            io.print("Studio: " + dvd.getStudio());
            io.print("User rating: " + dvd.getUserRating());
            io.print("");
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayRemoveDvdBanner() {
        io.print("=== Remove Dvd ===");
    }

    public void displayRemoveResult(DvD dvdRecord) {
        if(dvdRecord != null){
            io.print("DVD successfully removed.");
        }else{
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayExitBanner() {
        io.print("Good Bye!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    public void displayEditDvdBanner() {
        io.print("=== Edit DVD ===");
    }

    public void displayEditDvdSuccess() {
        io.readString(
                "DVD successfully Edited.  Please hit enter to continue");
    }

    public void displayEditReleaseDateBanner() {
        io.print("=== Edit DVD Release Date ===");
    }

    public void displayEditMpaaBanner() {
        io.print("=== Edit DVD MPAA rating ===");
    }

    public void displayEditDirectorNameBanner() {
        io.print("=== Edit DVD Director's Name ===");
    }

    public void displayEditStudio() {
        io.print("=== Edit DVD Studio ===");
    }

    public void displayEditUserRating() {
        io.print("=== Edit DVD User Rating ===");
    }

    public String getNewReleaseDate() {
        return io.readString("Please enter new release date.");
    }

    public String getNewMpaaRating() {
        return io.readString("Please enter new MPAA rating.");
    }

    public String getNewDirectorName() {
        return io.readString("Please enter new director's name.");
    }

    public String getNewUserRating() {
        return io.readString("Please enter new user rating.");
    }

    public String getNewStudio() {
        return io.readString("Please enter new studio.");
    }

    public void displayNullDVD() {
        io.print("No such DVD.");
        io.readString("Please hit enter to continue.");
    }
}
