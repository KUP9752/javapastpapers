package filesystems;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class DocFileUtils {

  /**
   * Compute the total size, in bytes, of the directory and all of its contents.
   *
   * @param directory A directory.
   * @return The size of this directory plus, the sum of the sizes of all files contained directly
   * or indirectly in this directory.
   */
  public static int getTotalDirectorySize(DocDirectory directory) {

    return directory.getSize()
            + directory.getDataFiles().stream()
                    .map(DocFile::getSize).reduce(Integer::sum).orElse(0)
            + directory.getDirectories().stream()
                    .map(DocFileUtils::getTotalDirectorySize).reduce(Integer::sum).orElse(0);
  }

  /**
   * Copy a named file between directories.
   *
   * @param src      A source directory.
   * @param dst      A destination directory.
   * @param filename The name of a file to be copied.
   * @return False if the source directory does not contain a file with the given name, or if the
   * destination directory already contains a file with the given name. Otherwise, create a
   * duplicate of the file with the given name in the source directory and add this duplicate to
   * the destination directory.
   */
  public static boolean copy(DocDirectory src, DocDirectory dst, String filename) {
    if (src.containsFile(filename)) {
      DocFile copyFile = src.getFile(filename).duplicate();
      dst.addFile(copyFile);
      return true;
    }
    return false;
//    return src.containsFile(filename)
  }

  /**
   * Locate a file containing a given byte and lying at or beneath a given file, if one exists.
   *
   * @param root     A file, to be recursively searched.
   * @param someByte A byte to be searched for.
   * @return An empty optional if no file at or beneath the given root file contains the given byte.
   * Otherwise, return an optional containing any such file.
   */
  public static Optional<DocDataFile> searchForByte(DocFile root, byte someByte) {
    if (root.isDataFile()) { //search a document
      DocDataFile rootFile = root.asDataFile();
      return (rootFile.containsByte(someByte))
              ? Optional.of(rootFile)
              : Optional.empty();

    } else if (root.isDirectory()) { // search the directory's documents
      DocDirectory rootDir = root.asDirectory();
      //tries to find the byte in the immediate files in this dir
      List<Optional<DocDataFile>> opts = rootDir.getAllFiles().stream()
              .map(file -> searchForByte(file, someByte))
              .filter(opt -> !opt.equals(Optional.empty())).toList();

      if (!opts.isEmpty()) return opts.get(0);
    }
    return Optional.empty();
  }
}
