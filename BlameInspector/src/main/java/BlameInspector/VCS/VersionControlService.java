package BlameInspector.VCS;


import java.util.ArrayList;
import java.util.HashMap;

public abstract class VersionControlService {

    protected HashMap<String, ArrayList<String>> filesInRepo;
    protected String repositoryURL;

    public abstract String getBlamedUserCommit(final String fileName, final String className,
                                               final int lineNumber) throws Exception;
    public abstract String getBlamedUserEmail(final String fileName, final String className,
                                              final int lineNumber) throws Exception;

    public String getRepositoryOwner(){
        String []urlParts = repositoryURL.split("/");
        return repositoryURL.split("/")[urlParts.length - 2];
    }

    public boolean containsFile(final String fileName){
        return filesInRepo.containsKey(fileName);
    }

    protected String getFilePath(final String fileName, final String className) {
        String pathPart = className.split("$")[0].replace(".", "\\");
        for (String path : filesInRepo.get(fileName)){
            if (path.contains(pathPart + fileName)){
                return path;
            }
        }
        return filesInRepo.get(fileName).get(0);
    }
}