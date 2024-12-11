Gem::Specification.new do |s|
  s.name          = 'num4probstdy'
  s.version       = '0.0.1'
  s.date          = '2024-12-10'
  s.summary       = "num for sales analysis"
  s.description   = "numerical solution for sales analysis."
  s.platform      = 'java'
  s.authors       = ["siranovel"]
  s.email         = "siranovel@gmail.com"
  s.homepage      = "http://github.com/siranovel/probstdy"
  s.license       = "MIT"
  s.files         = ["LICENSE", "Gemfile", "CHANGELOG.md"]
  s.files         += Dir.glob("{lib,ext}/**/*")
  s.extensions  = %w[Rakefile]
  s.add_development_dependency 'rake', '~> 12.3', '>= 12.3.3'
  s.add_development_dependency 'rake-compiler', '~> 1.2', '>= 1.2.5'
end

