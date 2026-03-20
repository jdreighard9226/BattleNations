/**
 * while reviewing scoping issues in  MapLoaderData, I looked at the warnings.
 * I saw an option to convert to a record class.
 * After reading https://docs.oracle.com/en/java/javase/17/language/records.html I decided that MapLoaderData could be converted to a record.
 */
package map;

import java.util.List;

public record MapLoaderData(Territory[][] territories, List<Region> regions) {
}
