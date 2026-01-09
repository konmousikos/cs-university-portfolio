# Sample Data (GPX Files)

This directory contains **sample GPX files** used for testing and demonstrating the functionality of the distributed activity tracking system.

The GPX files represent recorded physical activities and consist of sequences of GPS waypoints, as described in the project specification.

---

## Purpose of Sample Data

The sample data is provided to:

- Validate the correctness of GPX parsing
- Test the MapReduce-based processing pipeline
- Demonstrate parallel processing across multiple Worker nodes
- Enable reproducibility of experiments and results

---

## GPX File Structure

Each GPX file contains a single recorded activity and includes:

- Latitude and longitude coordinates
- Elevation data
- Timestamp information

Example structure:

```xml
<gpx>
  <wpt lat="..." lon="...">
    <ele>...</ele>
    <time>...</time>
  </wpt>
</gpx>
