function init() {
  var $ = go.GraphObject.make // for conciseness in defining templates in this function

  myDiagram = $(go.Diagram, 'myDiagramDiv', {
    layout: $(DoubleTreeLayout, {
      //vertical: true,  // default directions are horizontal
      // choose whether this subtree is growing towards the right or towards the left:
      directionFunction: function (n) {
        return n.data && n.data.dir !== 'left'
      },
      // controlling the parameters of each TreeLayout:
      //bottomRightOptions: { nodeSpacing: 0, layerSpacing: 20 },
      //topLeftOptions: { alignment: go.TreeLayout.AlignmentStart },
    }),
  })

  // define all of the gradient brushes
  var graygrad = $(go.Brush, 'Linear', { 0: '#F5F5F5', 1: '#F1F1F1' })
  var bluegrad = $(go.Brush, 'Linear', { 0: '#CDDAF0', 1: '#91ADDD' })
  var yellowgrad = $(go.Brush, 'Linear', { 0: '#FEC901', 1: '#FEA200' })
  var lavgrad = $(go.Brush, 'Linear', { 0: '#EF9EFA', 1: '#A570AD' })

  myDiagram.nodeTemplate = $(
    go.Node,
    'Auto',
    { isShadowed: false },
    // define the node's outer shape
    $(
      go.Shape,
      'RoundedRectangle',
      { fill: graygrad, stroke: '#D8D8D8' }, // default fill is gray
      new go.Binding('fill', 'color')
    ),
    // define the node's text
    $(
      go.TextBlock,
      { margin: 5, font: 'bold 11px Helvetica, bold Arial, sans-serif' },
      new go.Binding('text', 'key')
    )
  )

  myDiagram.linkTemplate = $(
    go.Link, // the whole link panel
    { selectable: false },
    $(go.Shape)
  ) // the link shape

  // create the model for the double tree; could be eiher TreeModel or GraphLinksModel
  myDiagram.model = new go.TreeModel([
    { key: 'Root', color: lavgrad },
    { key: 'Left1', parent: 'Root', dir: 'left', color: bluegrad },
    { key: 'leaf1', parent: 'Left1' },
    { key: 'leaf2', parent: 'Left1' },
    { key: 'Left2', parent: 'Left1', color: bluegrad },
    { key: 'leaf3', parent: 'Left2' },
    { key: 'leaf4', parent: 'Left2' },
    { key: 'leaf5', parent: 'Left1' },
    { key: 'Right1', parent: 'Root', dir: 'right', color: yellowgrad },
    { key: 'Right2', parent: 'Right1', color: yellowgrad },
    { key: 'leaf11', parent: 'Right2' },
    { key: 'leaf12', parent: 'Right2' },
    { key: 'leaf13', parent: 'Right2' },
    { key: 'leaf14', parent: 'Right1' },
    { key: 'leaf15', parent: 'Right1' },
    { key: 'Right3', parent: 'Root', dir: 'right', color: yellowgrad },
    { key: 'leaf16', parent: 'Right3' },
    { key: 'leaf17', parent: 'Right3' },
  ])
}
window.addEventListener('DOMContentLoaded', init)
