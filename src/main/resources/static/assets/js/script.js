function openFileDialog(filter, multiple) {
    const $element = $(document.createElement("input"));
    $element.attr("type", "file");
    $element.attr("accept", filter);
    if (multiple)
        $element.attr("multiple", "multiple");
    return $element;
}