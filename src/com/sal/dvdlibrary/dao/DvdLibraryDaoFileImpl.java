package com.sal.dvdlibrary.dao;

import com.sal.dvdlibrary.dto.DvD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DvdLibraryDaoFileImpl implements dvdLibraryDao {
    public final String DVD_FILE ;
    public static final String DELIMITER = "::";
    /*
    Hash Map to store and retrieve the dvd with title name
     */
    private Map<String, DvD> dvds = new HashMap<>();

    public DvdLibraryDaoFileImpl() { //no arg constructor typically used
        DVD_FILE = "dvdTest.txt";
    }
    public DvdLibraryDaoFileImpl(String libraryTextFile) {
        DVD_FILE = libraryTextFile;
    }

    public DvD addDvd(String title, DvD dvd) throws DvdLibraryDaoException
    {
        loadDvdFile();
        DvD newDvD = dvds.put(title, dvd);
        writeDvdFile();
        return newDvD;
    }

    /*
    This code gets all the DvDs objects out of the dvds map as a collection by calling the values() method.
     */
    public List<DvD> getAllDvds() throws DvdLibraryDaoException
    {
        loadDvdFile();
        return new ArrayList<DvD>(dvds.values());
    }

    /*
    This method is quite simple — we just ask the dvds map for the dvd object with the given title and return it
     */
    public DvD getDvd(String title)throws DvdLibraryDaoException
    {
        loadDvdFile();
        return dvds.get(title);
    }

    public DvD removeDvd(String title) throws DvdLibraryDaoException
    {
        loadDvdFile();
        DvD removedDvD = dvds.remove(title);
        writeDvdFile();
        return removedDvD;
    }



    /*
    Method to unmarshall the object or read a line of string
    from the line and convert it into an object
     */
    private DvD unmarshallDvd(String dvdAsText) {
        //implement
        // dvdAsText is expecting a line read in from our file.
        // For example, it might look like this:
        // Hereditary::01/21/2018::R::Ari_Aster::A24::100/100
        //
        // We then split that line on our DELIMITER - which we are using as ::
        // Leaving us with an array of Strings, stored in studentTokens.
        // Which should look like this:
        // _______________________________________________
        // |          |      |   | |         |   |       |
        // |Hereditary|01/21/2018|R|Ari_Aster|A24|100/100|
        // |          |      |   | |         |   |       |
        // _______________________________________________
        //  [0]       [1]    [2] [3][4]      [5] [6]
        String[] dvdTokens = dvdAsText.split(DELIMITER);

        // Given the pattern above, the dvd Title is in index 0 of the array.
        String dvdTitle = dvdTokens[0];

        // Which we can then use to create a new Student object to satisfy
        // the requirements of the Student constructor.
        DvD dvdFromFile = new DvD(dvdTitle);

        // However, there are 3 remaining tokens that need to be set into the
        // new student object. Do this manually by using the appropriate setters.

        // Index 1 - Release date
        dvdFromFile.setReleaseDate(dvdTokens[1]);

        // Index 2 - MPAA
        dvdFromFile.setMPAA(dvdTokens[2]);

        // Index 3 - Director's name
        dvdFromFile.setDirectorsName(dvdTokens[3]);

        // Index 4 - Studio
        dvdFromFile.setStudio(dvdTokens[4]);

        // Index 5 - User rating
        dvdFromFile.setUserRating(dvdTokens[5]);

        // We have now created a student! Return it!
        return dvdFromFile;
    }

    /*
    Method to Load file DVD_FILE into memory
     */
    private void loadDvdFile() throws DvdLibraryDaoException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(DVD_FILE)));
        } catch (FileNotFoundException e) {
            throw new DvdLibraryDaoException(
                    "-_- Could not load roster data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentStudent holds the most recent student unmarshalled
//        DvD currentDvD;
        // Go through DVD_FILE line by line, decoding each line into a
        // DVD object by calling the unmarshallDVD method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a Student
            DvD currentDVD = unmarshallDvd(currentLine);

            // We are going to use the student id as the map key for our student object.
            // Put currentStudent into the map using student id as the key
            dvds.put(currentDVD.getDvDTitle(), currentDVD);
        }
        // close scanner
        scanner.close();
    }

    private String marshallDvd(DvD aDvd) {
        // get DvD's title:
        String dvdAsText = aDvd.getDvDTitle() + DELIMITER;

        // get DvD's release date
        dvdAsText += aDvd.getReleaseDate() + DELIMITER;

        // get DvD's MPAA
        dvdAsText += aDvd.getMPAA() + DELIMITER;

        // get DvD's director
        dvdAsText += aDvd.getDirectorsName() + DELIMITER;

        // get DvD's studio
        dvdAsText += aDvd.getStudio() + DELIMITER;

        // get DvD's userRating - don't forget to skip the DELIMITER here.
        dvdAsText += aDvd.getUserRating();

        // We have now turned a student to text! Return it!
        return dvdAsText;
    }

    /**
     * Writes all Dvds in the library out to a DVD_FILE. See loadDvdFile
     * for file format.
     *
     * @throws Exception if an error occurs writing to the file
     */
    private void writeDvdFile() throws DvdLibraryDaoException {
        // NOTE FOR APPRENTICES: We are not handling the IOException - but
        // we are translating it to an application specific exception and
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to
        // handle any errors that occur.
        //implement

        // Write out the DvD objects to the DVD file.
        // NOTE TO THE APPRENTICES: We could just grab the dvd map,
        // get the Collection of dvd and iterate over them but we've
        // already created a method that gets a List of dvds so
        // we'll reuse it.
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(DVD_FILE));
        } catch (IOException e) {
            throw new DvdLibraryDaoException(
                    "Could not save DVD data.", e);
        }

        // Write out the DVD objects to the roster file.
        // NOTE TO THE APPRENTICES: We could just grab the dvd map,
        // get the Collection of DVDs and iterate over them, but we've
        // already created a method that gets a List of Students, so
        // we'll reuse it.
        String dvdAsText;
        List<DvD> dvdList = this.getAllDvds();
        for (DvD currentDvD : dvdList) {
            // turn a Student into a String
            dvdAsText = marshallDvd(currentDvD);
            // write the Student object to the file
            out.println(dvdAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

    public DvD editReleaseDate(String title, String newReleaseDate) throws DvdLibraryDaoException {
        loadDvdFile();
        DvD currentDVD = dvds.get(title);
        currentDVD.setReleaseDate(newReleaseDate);
        writeDvdFile();
        return currentDVD;
    }

    public DvD editMPAA(String title, String newMpaaRating) throws DvdLibraryDaoException {
        loadDvdFile();
        DvD currentDVD = dvds.get(title);
        currentDVD.setMPAA(newMpaaRating);
        writeDvdFile();
        return currentDVD;
    }

    public DvD editDirectorName(String title, String newDirectorName) throws DvdLibraryDaoException {
        loadDvdFile();
        DvD currentDVD = dvds.get(title);
        currentDVD.setDirectorsName(newDirectorName);
        writeDvdFile();
        return currentDVD;
    }

    public DvD editUserRating(String title, String newUserRating) throws DvdLibraryDaoException {
        loadDvdFile();
        DvD currentDVD = dvds.get(title);
        currentDVD.setUserRating(newUserRating);
        writeDvdFile();
        return currentDVD;
    }

    public DvD editStudio(String title, String newStudioName) throws DvdLibraryDaoException {
        loadDvdFile();
        DvD currentDVD = dvds.get(title);
        currentDVD.setStudio(newStudioName);
        writeDvdFile();
        return currentDVD;
    }
}
